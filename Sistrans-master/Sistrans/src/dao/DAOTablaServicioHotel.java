package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ServicioHotel;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOTablaServicioHotel {

	
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
	public DAOTablaServicioHotel() {
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

	public void registrarServicioHotel(ServicioHotel servicioHotel) throws SQLException, Exception
	{

//		if(servicioHotel.getDescripcion() == null || servicioHotel.getIdServicioHotel() == null
//                        || servicioHotel.getTipo() == null)
//		{
//			throw new Exception("hay campos nulos");
//		}
//		
		String sql = String.format("INSERT INTO %1$s.SERVICIOHOTEL (DESCRIPCION, IDSERVICIOHOTEL"
                        + ", TIPO) VALUES ('%2$s', %3$s, '%4$s')", 
				USUARIO, 
				servicioHotel.getDescripcion(), 
				servicioHotel.getIdServicioHotel(),
				servicioHotel.getTipo());
		System.out.println(sql);

		if (findServicioHotelById(servicioHotel.getIdServicioHotel())!= null) {
	
			throw new Exception("ya existe la servicioHotel en oferta");

			}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		     }	
        }
        
	public ArrayList<ServicioHotel> getServicioHoteles() throws SQLException 
        {
                ArrayList<ServicioHotel> servicioHoteles = new ArrayList<ServicioHotel>();
                
		String sql = String.format("SELECT * FROM %1$s.SERVICIOHOTEL WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			servicioHoteles.add(convertResultSetToServicioHotel(rs));
		}
		return servicioHoteles;
	}
	
	
	public ServicioHotel findServicioHotelById(Long id) throws SQLException, Exception 
	{
		ServicioHotel hab = null;

		String sql = String.format("SELECT * FROM %1$s.SERVICIOHOTEL WHERE IDSERVICIOHOTEL = %2$d"
                        , USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			hab = convertResultSetToServicioHotel(rs);
		}

		return hab;
	}	
	
	public void updateServicioHotel(ServicioHotel servicioHotel) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.SERVICIOHOTEL SET ", USUARIO));
		sql.append(String.format("TIPO = '%1$s' AND DESCRIPCION = '%2$s' "
                        + "AND IDSERVICIOHOTEL = '%3$s'", servicioHotel.getTipo(),
		servicioHotel.getDescripcion(), servicioHotel.getIdServicioHotel()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deleteServicioHotel(Long id) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.SERVICIOHOTEL WHERE IDSERVICIOHOTEL = %2$d"
                        , USUARIO, id);

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public ServicioHotel convertResultSetToServicioHotel(ResultSet resultSet) throws SQLException {		
		
		String descripcion = resultSet.getString("DESCRIPCION");
		Long idServicioHotel = resultSet.getLong("IDSERVICIOHOTEL");
		String tipo = resultSet.getString("TIPO");			
	
		ServicioHotel hab = new ServicioHotel(tipo, descripcion, idServicioHotel);
		return hab;
	}	
}



