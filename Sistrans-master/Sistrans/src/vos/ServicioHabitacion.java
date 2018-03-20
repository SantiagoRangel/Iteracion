package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ServicioHabitacion {

	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="idServicioHabitacion")
	private Long idServicioHabitacion;
        
	public ServicioHabitacion(@JsonProperty(value="tipo")String tipo,
			@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="idServicioHabitacion") Long idServicioHabitacion)
        {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.idServicioHabitacion = idServicioHabitacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdServicioHabitacion() {
		return idServicioHabitacion;
	}

	public void setIdServicioHabitacion(Long idServicioHabitacion) {
		this.idServicioHabitacion = idServicioHabitacion;
	}
}
