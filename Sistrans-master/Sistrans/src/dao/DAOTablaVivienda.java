package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Vivienda;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 * @author Grupo A - 16
 */
public class DAOTablaVivienda {

	
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
	public DAOTablaVivienda() {
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
	 * Metodo que inicializa la connection del DAO a la base de datos con la conexión que entra como parametro.
	 * @param con - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	public void registrarVivienda(Vivienda vivienda) throws SQLException, Exception
	{

//		if(vivienda.getDescripcion() == null || vivienda.getIdVivienda() == null || vivienda.getIdPersonaNatural() == null || 
//				vivienda.getPrecio() == null || vivienda.getTamano()== null || vivienda.getUbicacion()== null ||
//                        vivienda.getMenaje() == null || vivienda.getNumeroHabitaciones()==null)
//		{
//			throw new Exception("hay campos nulos");
//		}
		
		String sql = String.format("INSERT INTO %1$s.VIVIENDA (DESCRIPCION, UBICACION, MENAJE, NUMEROHABITACIONES, IDPERSONANATURAL, PRECIO, TAMANO, IDVIVIENDA) "
                                         + "VALUES ('%2$s', '%3$s', '%4$s', %5$s, %6$s, %7$s, %8$s, %9$s)", 
				USUARIO, 
				vivienda.getDescripcion(), 
				vivienda.getUbicacion(),
				vivienda.getMenaje(), 
                vivienda.getNumeroHabitaciones(), 
                vivienda.getIdPersonaNatural(), 
				vivienda.getPrecio(),
				vivienda.getTamano(),
				vivienda.getIdVivienda());
		System.out.println(sql);

		if (findViviendaById(vivienda.getIdVivienda())!= null) {
	
			throw new Exception("ya existe la vivienda");

			}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			}

	}
	
	
	
	public ArrayList<Vivienda> getViviendaes() throws SQLException, Exception {
		ArrayList<Vivienda> viviendaes = new ArrayList<Vivienda>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.VIVIENDA WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			viviendaes.add(convertResultSetToVivienda(rs));
		}
		return viviendaes;
	}
	
	
	public Vivienda findViviendaById(Long id) throws SQLException, Exception 
	{
		Vivienda hab = null;

		String sql = String.format("SELECT * FROM %1$s.VIVIENDA WHERE IDVIVIENDA = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			hab = convertResultSetToVivienda(rs);
		}

		return hab;
	}	
	
	public void updateVivienda(Vivienda vivienda) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.VIVIENDA SET ", USUARIO));
		sql.append(String.format("DESCRIPCION = '%1$s' AND UBICACION = '%2$s' AND MENAJE = '%3$s' AND NUMEROHABITACIONES = '%4$s' AND IDPERSONANATURAL = '%5$s' AND PRECIO = '%6$s' AND TAMANO = '%7$s' AND IDVIVIENDA = '%8$s'", 
                vivienda.getDescripcion(), vivienda.getUbicacion(), vivienda.getMenaje(),vivienda.getNumeroHabitaciones(),vivienda.getIdPersonaNatural(), vivienda.getPrecio(),vivienda.getTamano(), vivienda.getIdVivienda()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deleteVivienda(Long id) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.VIVIENDA WHERE IDVIVIENDA = %2$d", USUARIO, id);

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Vivienda convertResultSetToVivienda(ResultSet resultSet) throws SQLException {		
		
		String descripcion = resultSet.getString("DESCRIPCION");
		Long idVivienda = resultSet.getLong("IDVIVIENDA");
		Long idPersonaNatural = resultSet.getLong("IDPERSONANATURAL");
		String menaje = resultSet.getString("MENAJE");
		Integer numeroHabitaciones = resultSet.getInt("NUMEROHABITACIONES");
		String ubicacion = resultSet.getString("UBICACION");
                Double tamano = resultSet.getDouble("TAMANO");
                Double precio = resultSet.getDouble("PRECIO");
	
		Vivienda hab = new Vivienda(descripcion, ubicacion, menaje, numeroHabitaciones, idPersonaNatural, precio, tamano, idVivienda);
		return hab;
	}	
}

