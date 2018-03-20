package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaNatural extends Operador {

	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="uniandino")
	private Long uniandino;	
	
	public PersonaNatural(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idUsuario") Long idUsuario,
                        @JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="uniandino") Long uniandino) 
        {
		super(usuario, contrasena, idUsuario);
		this.nombre = nombre;
		this.uniandino = uniandino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getUniandino() {
		return uniandino;
	}

	public void setUniandino(long uniandino) {
		this.uniandino = uniandino;
	}	
}
