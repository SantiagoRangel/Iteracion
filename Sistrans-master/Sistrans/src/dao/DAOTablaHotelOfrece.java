package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.HotelOfrece;

/**
 * @author Grupo A - 16
 * Dao Servicio Habitacion para conectarse a la base de datos usando JDBC
 */
public class DAOTablaHotelOfrece {
	
	public final static String USUARIO = "ISIS2304A241810";
	
	private ArrayList<Object> recursos;	
	
	private Connection conn;
       
	public DAOTablaHotelOfrece() {
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
	
	public void registrarHotelOfrece(HotelOfrece servHab) throws SQLException, Exception {

		if(servHab.getIdServicioHotel() == null || servHab.getIdHotel() == null 
                        || servHab.getIdHotelOfrece() == null){
		  throw new Exception("esta incompleto");
		}
		
		String sql = String.format("INSERT INTO %1$s.HOTELOFRECE (IDHOTELOFRECE, IDHOTEL"
                        + ", IDSERVICIOHOTEL) VALUES (%2$s, '%3$s', '%4$s')", 
		  USUARIO, 
		  servHab.getIdHotel(), 
	          servHab.getIdServicioHotel(),
		  servHab.getIdHotelOfrece());
                System.out.println(sql);

                PreparedStatement prepStmt = conn.prepareStatement(sql);
                recursos.add(prepStmt);
                prepStmt.executeQuery();
	}
	
	
	
	public ArrayList<HotelOfrece> getServiciosHabitaciones() throws SQLException, Exception {
            
		ArrayList<HotelOfrece> servHabs = new ArrayList<HotelOfrece>();

		String sql = String.format("SELECT * FROM %1$s.HOTELOFRECE WHERE ROWNUM <= 50"
                        , USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
		  servHabs.add(convertResultSetToHotelOfrece(rs));
		}
		return servHabs;
	}
	
	
	public HotelOfrece findHotelOfreceById(Long id) throws SQLException, Exception{
            
		HotelOfrece servHab = null;

		String sql = String.format("SELECT * FROM %1$s.HOTELOFRECE WHERE "
                        + "IDHOTELOFRECE = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
		  servHab = convertResultSetToHotelOfrece(rs);
		}

		return servHab;
	}	
	
	/**public void updateHotelOfrece(HotelOfrece servHab) throws SQLException
                , Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(String.format("UPDATE %s.HOTELOFRECE SET", USUARIO));
		sql.append(String.format(" = '%1$s' AND DESCRIPCION = '%2$s' AND "
                        + "IDHOTELOFRECE = '%3$s'", servHab.getTipo(),
		servHab.getDescripcion(), servHab.getIdHotelOfrece()));
		
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}*/

	public void deleteHotelOfrece(HotelOfrece servHab) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.HOTELOFRECE "
                        + "WHERE IDHOTELOFRECE = %2$d", USUARIO
                        , servHab.getIdHotelOfrece());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public HotelOfrece convertResultSetToHotelOfrece(ResultSet resultSet) 
                throws SQLException {		
		
		Long idServicioHotel = resultSet.getLong("IDSERVICIOHOTEL");
                Long idHotel = resultSet.getLong("IDHOTEL");
		Long idHotelOfrece = resultSet.getLong("IDHABITACION");
	
		HotelOfrece servHab = new HotelOfrece(idHotelOfrece, idHotel
                        , idServicioHotel);
		return servHab;
	}	
}

