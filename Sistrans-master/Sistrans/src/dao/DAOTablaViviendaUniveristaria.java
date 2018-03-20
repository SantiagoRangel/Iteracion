package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.ViviendaUniversitaria;

public class DAOTablaViviendaUniveristaria {
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
	public DAOTablaViviendaUniveristaria() {
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


	public void registrarViviendaUniversitaria(ViviendaUniversitaria viviendaUniversitaria) throws SQLException, Exception {

		if( viviendaUniversitaria.getContrasena()== null || viviendaUniversitaria.getIdUsuario() == null || viviendaUniversitaria.getUsuario()== null|| viviendaUniversitaria.getNombre()== null|| viviendaUniversitaria.getDescripcion()== null
				|| viviendaUniversitaria.getNombre()== null|| viviendaUniversitaria.getHoraAtencion()== null)
		{
			throw new Exception("hay campos nulos");
		}
		
		String sql = String.format("INSERT INTO %1$s.HOTEL (CONTRASENA,"
				+ " IDUSUARIO, USARIO, NOMBRE, HORAATENCION, DESCRIPCION) VALUES (%2$s, '%3$s', '%4$s','%5$s','%6$s','%7$s')", 
				USUARIO, 
				viviendaUniversitaria.getContrasena(),
				viviendaUniversitaria.getIdUsuario(),
				viviendaUniversitaria.getUsuario(),
				viviendaUniversitaria.getNombre(),
				viviendaUniversitaria.getHoraAtencion(),
				viviendaUniversitaria.getDescripcion());
		String sql1= String.format("INSERT INTO %1$s.OPERADOR (CONTRASENA,"
				+ " IDUSUARIO, USARIO) VALUES (%2$s, '%3$s', '%4$s')", 
				USUARIO, 
				viviendaUniversitaria.getContrasena(),
				viviendaUniversitaria.getIdUsuario(),
				viviendaUniversitaria.getUsuario()
				);
	
	
		if (findViviendaUniversitariaById(viviendaUniversitaria.getIdUsuario())!=null && findViviendaUniversitariaByUsuario(viviendaUniversitaria.getUsuario() )!= null) {
			throw new Exception("Ya existe la viviendaUniversitaria");
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
	
	
	
	public ArrayList<ViviendaUniversitaria> getViviendaUniversitarias() throws SQLException, Exception {
		ArrayList<ViviendaUniversitaria> usuarios = new ArrayList<ViviendaUniversitaria>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.VIVIENDAUNIVERSITARIA WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			usuarios.add(convertResultSetToViviendaUniversitaria(rs));
		}
		return usuarios;
	}
	
	
	public ViviendaUniversitaria findViviendaUniversitariaById(Long id) throws SQLException, Exception 
	{
		ViviendaUniversitaria usu = null;

		String sql = String.format("SELECT * FROM %1$s.VIVIENDAUNIVERSITARIA WHERE IDVIVIENDAUNIVERSITARIA = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usu = convertResultSetToViviendaUniversitaria(rs);
		}

		return usu;
	}	
	
	public ViviendaUniversitaria findViviendaUniversitariaByUsuario(String pusuario) throws SQLException, Exception 
	{
		ViviendaUniversitaria usu = null;

		String sql = String.format("SELECT * FROM %1$s.VIVIENDAUNIVERSITARIA WHERE USARIO = %2$d", USUARIO, pusuario); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usu = convertResultSetToViviendaUniversitaria(rs);
		}

		return usu;
	}	
//	public void updateViviendaUniversitaria(Usuario usuario) throws SQLException, Exception {
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

	public void deleteViviendaUniversitaria(ViviendaUniversitaria viviendaUniversitaria) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.VIVIENDAUNIVERSITARIA WHERE IDVIVIENDAUNIVERSITARIA = %2$d", USUARIO, viviendaUniversitaria.getIdUsuario());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public ViviendaUniversitaria convertResultSetToViviendaUniversitaria(ResultSet resultSet) throws SQLException {
		
		
		String contrasena = resultSet.getString("CONTRASENA");
		Long idUsuario = resultSet.getLong("IDUSUARIO");
		String usuario = resultSet.getString("USUARIO");
		String nombre = resultSet.getString("NOMBRE");
		String horaAtencion = resultSet.getString("HORAATENCION");
		String descripcion = resultSet.getString("DESCRIPCION");

	
		ViviendaUniversitaria viviendaUniversitaria = new ViviendaUniversitaria(usuario, contrasena, idUsuario, nombre, horaAtencion, descripcion);
		return viviendaUniversitaria;
	}
	

}
