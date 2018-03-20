package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViviendaUniversitaria extends Operador {
    
	@JsonProperty(value="horaAtencion")
	private String horaAtencion;
	
	@JsonProperty(value="nombre")
	private String nombre;	
	
	@JsonProperty(value="descripcion")
	private String descripcion;

	public ViviendaUniversitaria(@JsonProperty(value="usuario") String usuario,
			@JsonProperty(value="contrasena")String contrasena,
                        @JsonProperty(value="idUsuario") Long idUsuario
			,@JsonProperty(value="horaAtencion")String horaAtencion,
                        @JsonProperty(value="nombre") String nombre,
			 @JsonProperty(value="descripcion")String descripcion)
	{
		super(usuario, contrasena, idUsuario);
		this.descripcion = descripcion;
		this.horaAtencion = horaAtencion;
		this.nombre =nombre;
	}
        
	public String getDescripcion() {
		return descripcion;
	}
        
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
        
	public String getHoraAtencion() {
		return horaAtencion;
	}

	public void setHoraAtencion(String horaAtencion) {
		this.horaAtencion = horaAtencion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
