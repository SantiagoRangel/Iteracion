package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Habitacion {

	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="precio")
	private Long precio;
	
	@JsonProperty(value="idHabitacion")
	private Long idHabitacion;
	
	@JsonProperty(value="tamano")
	private Long tamano;
	
	@JsonProperty(value="idOperador")
	private Long idOperador;
	
	public Habitacion(@JsonProperty(value="ubicacion")String ubicacion,
                @JsonProperty(value="descripcion") String descripcion,
                @JsonProperty(value="precio") Long precio,
		@JsonProperty(value="idHabitacion") Long idHabitacion, 
                @JsonProperty(value="tamano") Long tamano,
                @JsonProperty(value="idOperador") Long idOperador) 
        {
		super();
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.idHabitacion = idHabitacion;
		this.tamano = tamano;
		this.idOperador = idOperador;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public Long getIdHabitacion() {
		return idHabitacion;
	}
        
	public Long getTamano() {
		return tamano;
	}

	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
}
