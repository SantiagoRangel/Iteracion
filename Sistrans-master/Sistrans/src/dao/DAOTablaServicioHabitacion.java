package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.ServicioHabitacion;

/**
 * @author Grupo A - 16
 * Dao Servicio Habitacion para conectarse a la base de datos usando JDBC
 */
public class DAOTablaServicioHabitacion {
	
	public final static String USUARIO = "ISIS2304A241810";
	
	private ArrayList<Object> recursos;	
	
	private Connection conn;
       
	public DAOTablaServicioHabitacion() {
		recursos = new ArrayList<Object>();
	} 
	
	public void cerrarRecursos() {
	        for(Object ob : recursos){
	          if(ob instanceof PreparedStatement){
	            try {
		      ((PreparedStatement) ob).close();
	            } catch (Exception ex) {
		      ex.printStackTrace();
                    }
	          }
                }
	}
        
	public void setConn(Connection con){
		this.conn = con;
	}
	
	public void registrarServicioHabitacion(ServicioHabitacion servHab) throws SQLException, Exception {

		if(servHab.getTipo() == null || servHab.getDescripcion() == null 
                        || servHab.getIdServicioHabitacion() == null){
		  throw new Exception("esta incompleto");
		}
		
		String sql = String.format("INSERT INTO %1$s.SERVICIOHABITACION (TIPO, DESCRIPCION"
                        + ", IDSERVICIOHABITACION) VALUES (%2$s, '%3$s', '%4$s')", 
		  USUARIO, 
		  servHab.getTipo(), 
	          servHab.getDescripcion(),
		  servHab.getIdServicioHabitacion());
                System.out.println(sql);

                PreparedStatement prepStmt = conn.prepareStatement(sql);
                recursos.add(prepStmt);
                prepStmt.executeQuery();
	}
	
	
	
	public ArrayList<ServicioHabitacion> getServiciosHabitaciones() throws SQLException, Exception {
            
		ArrayList<ServicioHabitacion> servHabs = new ArrayList<ServicioHabitacion>();

		String sql = String.format("SELECT * FROM %1$s.SERVICIOHABITACION WHERE ROWNUM <= 50"
                        , USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
		  servHabs.add(convertResultSetToServicioHabitacion(rs));
		}
		return servHabs;
	}
	
	
	public ServicioHabitacion findServicioHabitacionById(Long id) throws SQLException, Exception{
            
		ServicioHabitacion servHab = null;

		String sql = String.format("SELECT * FROM %1$s.SERVICIOHABITACION WHERE "
                        + "IDSERVICIOHABITACION = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
		  servHab = convertResultSetToServicioHabitacion(rs);
		}

		return servHab;
	}	
	
	public void updateServicioHabitacion(ServicioHabitacion servHab) throws SQLException
                , Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.SERVICIOHABITACION SET", USUARIO));
		sql.append(String.format("TIPO = '%1$s' AND DESCRIPCION = '%2$s' AND "
                        + "IDSERVICIOHABITACION = '%3$s'", servHab.getTipo(),
		servHab.getDescripcion(), servHab.getIdServicioHabitacion()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	public void deleteServicioHabitacion(ServicioHabitacion servHab) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.SERVICIOHABITACION "
                        + "WHERE IDSERVICIOHABITACION = %2$d", USUARIO
                        , servHab.getIdServicioHabitacion());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public ServicioHabitacion convertResultSetToServicioHabitacion(ResultSet resultSet) 
                throws SQLException {		
		
		String descripcion = resultSet.getString("DESCRIPCION");
                String tipo = resultSet.getString("TIPO");
		Long idServicioHabitacion = resultSet.getLong("IDHABITACION");
	
		ServicioHabitacion servHab = new ServicioHabitacion(descripcion, tipo
                        , idServicioHabitacion);
		return servHab;
	}	
}
