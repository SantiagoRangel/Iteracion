package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Hostal;

public class DAOTablaHostal {

	
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
	public DAOTablaHostal() {
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


	public void registrarHostal(Hostal hostal) throws SQLException, Exception {

		if( hostal.getContrasena()== null || hostal.getIdUsuario() == null || hostal.getUsuario()== null
				|| hostal.getNombre()== null|| hostal.getDescripcion()== null
				|| hostal.getNombre()== null|| hostal.getUbicacion()== null|| hostal.getHoraAtencion()== null)
		{
			throw new Exception("hay campos nulos");
		}
		
		String sql = String.format("INSERT INTO %1$s.HOSTAL (CONTRASENA,"
				+ " IDUSUARIO, USARIO, NOMBRE, UBICACION, DESCRIPCION,HORAATENCION) VALUES (%2$s, '%3$s', '%4$s','%5$s','%6$s','%7$s','%8$s')", 
				USUARIO, 
				hostal.getContrasena(),
				hostal.getIdUsuario(),
				hostal.getUsuario(),
				hostal.getNombre(),
				hostal.getUbicacion(),
				hostal.getDescripcion(),
				hostal.getHoraAtencion());
		String sql1= String.format("INSERT INTO %1$s.OPERADOR (CONTRASENA,"
				+ " IDUSUARIO, USARIO) VALUES (%2$s, '%3$s', '%4$s')", 
				USUARIO, 
				hostal.getContrasena(),
				hostal.getIdUsuario(),
				hostal.getUsuario()
				);
		if (findHostalById(hostal.getIdUsuario())!=null && findHostalByUsuario(hostal.getUsuario() )!= null) {
			throw new Exception("Ya existe el hostal");
		}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);

			recursos.add(prepStmt1);
			recursos.add(prepStmt);

			prepStmt.executeQuery();
		}
		
System.out.println(sql);


	}
	
	
	
	public ArrayList<Hostal> getHostales() throws SQLException, Exception {
		ArrayList<Hostal> hostales = new ArrayList<Hostal>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.HOSTAL WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			hostales.add(convertResultSetToHostal(rs));
		}
		return hostales;
	}
	
	
	public Hostal findHostalById(Long id) throws SQLException, Exception 
	{
		Hostal usu = null;

		String sql = String.format("SELECT * FROM %1$s.HOSTAL WHERE IDHOSTAL = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usu = convertResultSetToHostal(rs);
		}

		return usu;
	}	
	
	public Hostal findHostalByUsuario(String pusuario) throws SQLException, Exception 
	{
		Hostal usu = null;

		String sql = String.format("SELECT * FROM %1$s.HOSTAL WHERE USARIO = %2$d", USUARIO, pusuario); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usu = convertResultSetToHostal(rs);
		}

		return usu;
	}	
//	public void updateHostal(Usuario usuario) throws SQLException, Exception {
//
//		StringBuilder sql = new StringBuilder();
//		sql.append(String.format("UPDATE %s.HABITACION SET ", USUARIO));
//		sql.append(String.format("UBICACION = '%1$s' AND DESCRIPCION = '%2$s' AND PRECIO = '%3$s'"
//				+ " AND IDHABITACION = '%4$s' AND TAMANO = '%5$s' AND IDOPERADOR = '%6$s' ", habitacion.getUbicacion(),
//		habitacion.getDescripcion(), habitacion.getPrecio(),habitacion.getIdHabitacion(),habitacion.getTamano(), habitacion.getIdOperador() ));
//		
//		System.out.println(sql);
//		
//		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
//		recursos.add(prepStmt);
//		prepStmt.executeQuery();
//	}

	public void deleteHostal(Hostal hostal) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.HOSTAL WHERE IDHOSTAL = %2$d", USUARIO, hostal.getIdUsuario());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Hostal convertResultSetToHostal(ResultSet resultSet) throws SQLException {
		
		
		String contrasena = resultSet.getString("CONTRASENA");
		Long idUsuario = resultSet.getLong("IDUSUARIO");
		String usuario = resultSet.getString("USUARIO");
		String nombre = resultSet.getString("NOMBRE");
		String ubicacion = resultSet.getString("UBICACION");
		String descripcion = resultSet.getString("DESCRIPCION");
		String horaAtencion = resultSet.getString("HORAATENCION");

	
		Hostal hostal = new Hostal(usuario, contrasena, idUsuario, horaAtencion, nombre, ubicacion, descripcion);
		return hostal;
	}
	
}
