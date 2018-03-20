package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Hotel extends Operador{
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	@JsonProperty(value="descripcion")
	private String descripcion;

	public Hotel(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idUsuario") Long idUsuario
			,@JsonProperty(value="nombre") String nombre,
			@JsonProperty(value="ubicacion")String ubicacion, 
                        @JsonProperty(value="descripcion")String descripcion)
	{
		super(usuario, contrasena, idUsuario);
		this.descripcion = descripcion;
		this.nombre =nombre;
		this.ubicacion = ubicacion;
	}
        
	public String getDescripcion() {
		return descripcion;
	}
        
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
        
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
