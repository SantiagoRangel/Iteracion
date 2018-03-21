package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Apartamento;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOTablaApartamento {

	
	public final static String USUARIO = "ISIS2304A241810";
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;
	
	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Metodo constructor que crea DAOIngrediente
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaApartamento() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * Metodo que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que 
         * entra como parametro.
	 * @param con - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	public void registrarApartamento(Apartamento apartamento) throws SQLException, Exception
	{
//		if(apartamento.getDescripcion() == null || apartamento.getIdApartamento() == null
//                        || apartamento.getIdPersonaNatural() == null || apartamento.getPrecio() == null 
//                        || apartamento.getTamano()== null || apartamento.getUbicacion()== null )
//		{
//			throw new Exception("hay campos nulos");
//		}
		
		String sql = String.format("INSERT INTO %1$s.APARTAMENTO (DESCRIPCION, IDAPARTAMENTO"
                        + ", IDPERSONANATURAL, PRECIO, TAMANO, UBICACION) "
                        + "VALUES ('%2$s', %3$s, %4$s, %5$s, %6$s, '%7$s')", 
				USUARIO, 
				apartamento.getDescripcion(), 
				apartamento.getIdApartamento(),
				apartamento.getIdPersonaNatural(), 
				apartamento.getPrecio(),
				apartamento.getTamano(),
				apartamento.getUbicacion());
		System.out.println(sql);

		if (findApartamentoById(apartamento.getIdApartamento())!= null) {
	
			throw new Exception("ya existe la apartamento en oferta");

			}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		     }	
        }
        
	public ArrayList<Apartamento> getApartamentos() throws SQLException 
        {
                ArrayList<Apartamento> apartamentoes = new ArrayList<Apartamento>();
                
		String sql = String.format("SELECT * FROM %1$s.APARTAMENTO WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			apartamentoes.add(convertResultSetToApartamento(rs));
		}
		return apartamentoes;
	}
	
	
	public Apartamento findApartamentoById(Long id) throws SQLException, Exception 
	{
		Apartamento hab = null;

		String sql = String.format("SELECT * FROM %1$s.APARTAMENTO WHERE IDAPARTAMENTO = %2$d"
                        , USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			hab = convertResultSetToApartamento(rs);
		}

		return hab;
	}	
	
	public void updateApartamento(Apartamento apartamento) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.APARTAMENTO SET ", USUARIO));
		sql.append(String.format("UBICACION = '%1$s' AND DESCRIPCION = '%2$s' "
                        + "AND PRECIO = '%3$s' AND IDAPARTAMENTO = '%4$s' AND TAMANO = '%5$s' "
                        + "AND IDPERSONANATURAL = '%6$s' ", apartamento.getUbicacion(),
		apartamento.getDescripcion(), apartamento.getPrecio(),apartamento.getIdApartamento()
                        ,apartamento.getTamano(), apartamento.getIdPersonaNatural() ));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deleteApartamento(Long id) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.APARTAMENTO WHERE IDAPARTAMENTO = %2$d"
                        , USUARIO, id);

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Apartamento convertResultSetToApartamento(ResultSet resultSet) throws SQLException {		
		
		String descripcion = resultSet.getString("DESCRIPCION");
		Long idApartamento = resultSet.getLong("IDAPARTAMENTO");
		Long idPersonaNatural = resultSet.getLong("IDPERSONANATURAL");
		Long precio = resultSet.getLong("PRECIO");
		Long tamano = resultSet.getLong("TAMANO");
		String ubicacion = resultSet.getString("UBICACION");	
	
		Apartamento hab = new Apartamento(ubicacion, descripcion, precio, idApartamento, tamano
                        , idPersonaNatural);
		return hab;
	}	
}


