package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Operador;
import vos.Usuario;

public class DAOTablaOperador {
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
	public DAOTablaOperador() {
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


	public void registrarOperador(Operador operador) throws SQLException, Exception
	{

		if( operador.getContrasena()== null || operador.getIdUsuario() == null || operador.getUsuario()== null)
		{
			throw new Exception("hay campos nulos");
		}
		
		String sql = String.format("INSERT INTO %1$s.OPERADOR (CONTRASENA,"
				+ " IDOPERADOR, USUARIO) VALUES (%2$s, '%3$s', '%4$s')", 
				USUARIO, 
				operador.getContrasena(),
				operador.getIdUsuario(),
				operador.getUsuario());
	
		if (findOperadorById(operador.getIdUsuario())!=null && findOperadorByUsuario(operador.getUsuario() )!= null) {
			throw new Exception("Ya existe el operador");
		}
		else {
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
System.out.println(sql);


	}
	
	
	
	public ArrayList<Operador> getOperadores() throws SQLException, Exception {
		ArrayList<Operador> habitaciones = new ArrayList<Operador>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.OPERADOR WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			habitaciones.add(convertResultSetToOperador(rs));
		}
		return habitaciones;
	}
	
	
	public Operador findOperadorById(Long id) throws SQLException, Exception 
	{
		Operador op = null;

		String sql = String.format("SELECT * FROM %1$s.OPERADOR WHERE IDOPERADOR = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			op = convertResultSetToOperador(rs);
		}

		return op;
	}	
	
	public Usuario findOperadorByUsuario(String pusuario) throws SQLException, Exception 
	{
		Operador op = null;

		String sql = String.format("SELECT * FROM %1$s.OPERADOR WHERE USUARIO = %2$d", USUARIO, pusuario); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			op = convertResultSetToOperador(rs);
		}

		return op;
	}	
	
//	public void updateOperador(Habitacin habitacion) throws SQLException, Exception {
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

	public void deleteOperador(Operador operador) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.OPERADOR WHERE IDOPERADOR = %2$d", USUARIO, operador.getIdUsuario());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public Operador convertResultSetToOperador(ResultSet resultSet) throws SQLException {
		
		
		
		String contrasena = resultSet.getString("CONTRASENA");
		Long idUsuario = resultSet.getLong("IDUSUARIO");
		String usuario = resultSet.getString("USUARIO");
		
	
	
		Operador op = new Operador(usuario, contrasena, idUsuario);
		return op;
	}
	
	
	
	
}
