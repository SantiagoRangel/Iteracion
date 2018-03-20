package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario {

	@JsonProperty(value="usuario")
	private String usuario;
	
	@JsonProperty(value="contrasena")	
	private String contrasena;
	
	@JsonProperty(value="idUsuario")
	private Long idUsuario;	
	
	public Usuario(@JsonProperty(value="usuario") String usuario, 
                @JsonProperty(value="contrasena")String contrasena,
                @JsonProperty(value="idUsuario") Long idUsuario) 
        {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.idUsuario = idUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
