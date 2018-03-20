package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.HabitacionOfrece;

/**
 * @author Grupo A - 16
 * Dao Servicio Habitacion para conectarse a la base de datos usando JDBC
 */
public class DAOTablaHabitacionOfrece {
	
	public final static String USUARIO = "ISIS2304A241810";
	
	private ArrayList<Object> recursos;	
	
	private Connection conn;
       
	public DAOTablaHabitacionOfrece() {
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
	
	public void registrarHabitacionOfrece(HabitacionOfrece servHab) throws SQLException, Exception {

		if(servHab.getIdServicioHabitacion() == null || servHab.getIdHabitacion() == null 
                        || servHab.getIdHabitacionOfrece() == null){
		  throw new Exception("esta incompleto");
		}
		
		String sql = String.format("INSERT INTO %1$s.HABITACIONOFRECE (IDHABITACIONOFRECE, IDHABITACION"
                        + ", IDSERVICIOHABITACION) VALUES (%2$s, '%3$s', '%4$s')", 
		  USUARIO, 
		  servHab.getIdHabitacion(), 
	          servHab.getIdServicioHabitacion(),
		  servHab.getIdHabitacionOfrece());
                System.out.println(sql);

                PreparedStatement prepStmt = conn.prepareStatement(sql);
                recursos.add(prepStmt);
                prepStmt.executeQuery();
	}
	
	
	
	public ArrayList<HabitacionOfrece> getServiciosHabitaciones() throws SQLException, Exception {
            
		ArrayList<HabitacionOfrece> servHabs = new ArrayList<HabitacionOfrece>();

		String sql = String.format("SELECT * FROM %1$s.HABITACIONOFRECE WHERE ROWNUM <= 50"
                        , USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
		  servHabs.add(convertResultSetToHabitacionOfrece(rs));
		}
		return servHabs;
	}
	
	
	public HabitacionOfrece findHabitacionOfreceById(Long id) throws SQLException, Exception{
            
		HabitacionOfrece servHab = null;

		String sql = String.format("SELECT * FROM %1$s.HABITACIONOFRECE WHERE "
                        + "IDHABITACIONOFRECE = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
		  servHab = convertResultSetToHabitacionOfrece(rs);
		}

		return servHab;
	}	
	
	/**public void updateHabitacionOfrece(HabitacionOfrece servHab) throws SQLException
                , Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.HABITACIONOFRECE SET", USUARIO));
		sql.append(String.format(" = '%1$s' AND DESCRIPCION = '%2$s' AND "
                        + "IDHABITACIONOFRECE = '%3$s'", servHab.getTipo(),
		servHab.getDescripcion(), servHab.getIdHabitacionOfrece()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}*/

	public void deleteHabitacionOfrece(HabitacionOfrece servHab) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.HABITACIONOFRECE "
                        + "WHERE IDHABITACIONOFRECE = %2$d", USUARIO
                        , servHab.getIdHabitacionOfrece());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public HabitacionOfrece convertResultSetToHabitacionOfrece(ResultSet resultSet) 
                throws SQLException {		
		
		Long idServicioHabitacion = resultSet.getLong("IDSERVICIOHABITACION");
                Long idHabitacion = resultSet.getLong("IDHABITACION");
		Long idHabitacionOfrece = resultSet.getLong("IDHABITACION");
	
		HabitacionOfrece servHab = new HabitacionOfrece(idHabitacionOfrece, idHabitacion
                        , idServicioHabitacion);
		return servHab;
	}	
}


