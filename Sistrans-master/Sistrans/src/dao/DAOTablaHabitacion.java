package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Habitacion;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la 
 * aplicación
 * @author Grupo A - 16*/
public class DAOTablaHabitacion {

	
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
	public DAOTablaHabitacion() {
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

	public void registrarHabitacion(Habitacion habitacion) throws SQLException, Exception
	{

		if(habitacion.getDescripcion() == null || habitacion.getIdHabitacion() == null
                        || habitacion.getIdOperador() == null || habitacion.getPrecio() == null 
                        || habitacion.getTamano()== null || habitacion.getUbicacion()== null )
		{
			throw new Exception("hay campos nulos");
		}
		
		String sql = String.format("INSERT INTO %1$s.HABITACION (DESCRIPCION, IDHABITACION"
                        + ", IDOPERADOR, PRECIO, TAMANO, UBICACION) "
                        + "VALUES ('%2$s', %3$s, %4$s, %5$s, %6$s, '%7$s')", 
				USUARIO, 
				habitacion.getDescripcion(), 
				habitacion.getIdHabitacion(),
				habitacion.getIdOperador(), 
				habitacion.getPrecio(),
				habitacion.getTamano(),
				habitacion.getUbicacion());
		System.out.println(sql);

//		if (findHabitacionById(habitacion.getIdHabitacion())!= null) {
//	
//			throw new Exception("ya existe la habitacion en oferta");
//
//			}
//		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
//		     }	
        }
        
	public ArrayList<Habitacion> getHabitaciones() throws SQLException 
        {
                ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
                
		String sql = String.format("SELECT * FROM %1$s.HABITACION WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToHabitacion(rs));
		}
		return habitaciones;
	}
	
	
	public Habitacion findHabitacionById(Long id) throws SQLException, Exception 
	{
		Habitacion hab = null;

		String sql = String.format("SELECT * FROM %1$s.HABITACION WHERE IDHABITACION = %2$d"
                        , USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			hab = convertResultSetToHabitacion(rs);
		}

		return hab;
	}	
	
	public void updateHabitacion(Habitacion habitacion) throws SQLException, Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.HABITACION SET ", USUARIO));
		sql.append(String.format("UBICACION = '%1$s' AND DESCRIPCION = '%2$s' "
                        + "AND PRECIO = '%3$s' AND IDHABITACION = '%4$s' AND TAMANO = '%5$s' "
                        + "AND IDOPERADOR = '%6$s' ", habitacion.getUbicacion(),
		habitacion.getDescripcion(), habitacion.getPrecio(),habitacion.getIdHabitacion()
                        ,habitacion.getTamano(), habitacion.getIdOperador() ));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deleteHabitacion(Long id) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.HABITACION WHERE IDHABITACION = %2$d"
                        , USUARIO,id);

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Habitacion convertResultSetToHabitacion(ResultSet resultSet) throws SQLException {		
		
		String descripcion = resultSet.getString("DESCRIPCION");
		Long idHabitacion = resultSet.getLong("IDHABITACION");
		Long idOperador = resultSet.getLong("IDOPERADOR");
		Long precio = resultSet.getLong("PRECIO");
		Long tamano = resultSet.getLong("TAMANO");
		String ubicacion = resultSet.getString("UBICACION");	
	
		Habitacion hab = new Habitacion(ubicacion, descripcion, precio, idHabitacion, tamano
                        , idOperador);
		return hab;
	}	
}


