package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Usuario;

public class DAOTablaUsuario {

	
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
	public DAOTablaUsuario() {
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


	public void registrarUsuario(Usuario usuario) throws SQLException, Exception {

		if( usuario.getContrasena()== null || usuario.getIdUsuario() == null || usuario.getUsuario()== null)
		{
			throw new Exception("hay campos nulos");
		}
		
		String sql = String.format("INSERT INTO %1$s.USUARIO (CONTRASENA,"
				+ " IDUSUARIO, USARIO) VALUES (%2$s, '%3$s', '%4$s')", 
				USUARIO, 
				usuario.getContrasena(),
				usuario.getIdUsuario(),
				usuario.getUsuario());
	
		if (findUsuarioById(usuario.getIdUsuario())!=null && findUsuarioByUsuario(usuario.getUsuario() )!= null) {
			throw new Exception("Ya existe el usuario");
		}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
System.out.println(sql);


	}
	
	
	
	public ArrayList<Usuario> getUsuarios() throws SQLException, Exception {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.USUARIO WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			usuarios.add(convertResultSetToUsuario(rs));
		}
		return usuarios;
	}
	
	
	public Usuario findUsuarioById(Long id) throws SQLException, Exception 
	{
		Usuario usu = null;

		String sql = String.format("SELECT * FROM %1$s.USUARIO WHERE IDUSUARIO = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usu = convertResultSetToUsuario(rs);
		}

		return usu;
	}	
	
	public Usuario findUsuarioByUsuario(String pusuario) throws SQLException, Exception 
	{
		Usuario usu = null;

		String sql = String.format("SELECT * FROM %1$s.USUARIO WHERE USUARIO = %2$d", USUARIO, pusuario); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			usu = convertResultSetToUsuario(rs);
		}

		return usu;
	}	
//	public void updateUsuario(Usuario usuario) throws SQLException, Exception {
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

	public void deleteUsuario(Usuario usuario) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.USUARIO WHERE IDUSUARIO = %2$d", USUARIO, usuario.getIdUsuario());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Usuario convertResultSetToUsuario(ResultSet resultSet) throws SQLException {
		
		
		String contrasena = resultSet.getString("CONTRASENA");
		Long idUsuario = resultSet.getLong("IDUSUARIO");
		String usuario = resultSet.getString("USUARIO");
		
	
	
		Usuario usu = new Usuario(usuario, contrasena, idUsuario);
		return usu;
	}
	
	
	
}
