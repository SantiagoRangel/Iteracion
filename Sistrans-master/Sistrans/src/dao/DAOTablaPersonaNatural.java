package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.PersonaNatural;

public class DAOTablaPersonaNatural {

	
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
	public DAOTablaPersonaNatural() {
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


	public void registrarPersonaNatural(PersonaNatural personaNatural) throws SQLException, Exception {

		if( personaNatural.getContrasena()== null || personaNatural.getIdUsuario() == null || personaNatural.getUsuario()== null
				|| personaNatural.getNombre()== null|| personaNatural.getUniandino() == null)
		{
			throw new Exception("hay campos nulos");
		}
		
		String sql = String.format("INSERT INTO %1$s.USUARIO (CONTRASENA,"
				+ " IDUSUARIO, USARIO, NOMBRE, UNIANDINO) VALUES (%2$s, '%3$s', '%4$s',%5$s', '%6$s')", 
				USUARIO, 
				personaNatural.getContrasena(),
				personaNatural.getIdUsuario(),
				personaNatural.getUsuario(),
				personaNatural.getNombre(),
				personaNatural.getUniandino());
		String sql1= String.format("INSERT INTO %1$s.OPERADOR (CONTRASENA,"
				+ " IDUSUARIO, USARIO) VALUES (%2$s, '%3$s', '%4$s')", 
				USUARIO, 
				personaNatural.getContrasena(),
				personaNatural.getIdUsuario(),
				personaNatural.getUsuario()
				);
	
	
		if (findPersonaNaturalById(personaNatural.getIdUsuario())!=null && findPersonaNaturalByUsuario(personaNatural.getUsuario() )!= null) {
			throw new Exception("Ya existe la personaNatural");
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
	
	
	
	public ArrayList<PersonaNatural> getPersonaNaturals() throws SQLException, Exception {
		ArrayList<PersonaNatural> personaNaturals = new ArrayList<PersonaNatural>();

		//Aclaracion: Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
		String sql = String.format("SELECT * FROM %1$s.PERSONANATURAL WHERE ROWNUM <= 50", USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			personaNaturals.add(convertResultSetToPersonaNatural(rs));
		}
		return personaNaturals;
	}
	
	
	public PersonaNatural findPersonaNaturalById(Long id) throws SQLException, Exception 
	{
		PersonaNatural personaNatural = null;

		String sql = String.format("SELECT * FROM %1$s.PERSONANATURAL WHERE IDPERSONANATURAL = %2$d", USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			personaNatural = convertResultSetToPersonaNatural(rs);
		}

		return personaNatural;
	}	
	
	public PersonaNatural findPersonaNaturalByUsuario(String pusuario) throws SQLException, Exception 
	{
		PersonaNatural personaNatural = null;

		String sql = String.format("SELECT * FROM %1$s.PERSONANATURAL WHERE USUARIO = %2$d", USUARIO, pusuario); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			personaNatural = convertResultSetToPersonaNatural(rs);
		}

		return personaNatural;
	}	
//	public void updatePersonaNatural(Usuario usuario) throws SQLException, Exception {
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

	public void deletePersonaNatural(PersonaNatural personaNatural) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.PERSONANATURAL WHERE IDPERSONANATURAL = %2$d", USUARIO, personaNatural.getIdUsuario());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public PersonaNatural convertResultSetToPersonaNatural(ResultSet resultSet) throws SQLException {
		
		
		String contrasena = resultSet.getString("CONTRASENA");
		Long idUsuario = resultSet.getLong("IDUSUARIO");
		String usuario = resultSet.getString("USUARIO");
		String nombre = resultSet.getString("NOMBRE");
		Long uniandino = resultSet.getLong("UNIANDINO");

	
		PersonaNatural personaNatural = new PersonaNatural(usuario, contrasena, idUsuario, nombre,uniandino);
		return personaNatural;
	}
	
	
}
