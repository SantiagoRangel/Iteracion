package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Vivienda {

	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	@JsonProperty(value="menaje")
	private String menaje;
	
	@JsonProperty(value="numeroHabitaciones")
	private Integer numeroHabitaciones;
	
	@JsonProperty(value="idPersonaNatural")
	private Long idPersonaNatural;
        
        @JsonProperty(value="idVivienda")
	private Long idVivienda;
	
	@JsonProperty(value="precio")
	private Double precio;
        
        @JsonProperty(value="tamano")
	private Double tamano;

	public Vivienda(@JsonProperty(value="descripcion")
	 String descripcion, @JsonProperty(value="ubicacion")
	 String ubicacion, @JsonProperty(value="menaje")
	 String menaje, @JsonProperty(value="numeroHabitaciones")
	 Integer numeroHabitaciones, @JsonProperty(value="idPersonaNatural")
	 Long idPersonaNatural, @JsonProperty(value="precio")
	 Double precio, @JsonProperty(value="tamano")
	 Double tamano, @JsonProperty(value="idVivienda")
	 Long idVivienda)
	{
		this.descripcion = descripcion;
		this.ubicacion= ubicacion;
		this.menaje= menaje;
		this.numeroHabitaciones = numeroHabitaciones;
		this.idPersonaNatural = idPersonaNatural;
		this.precio = precio;
                this.tamano = tamano;
                this.idVivienda = idVivienda;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getMenaje() {
		return menaje;
	}

	public void setMenaje(String menaje) {
		this.menaje = menaje;
	}

	public Integer getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	public void setNumeroHabitaciones(Integer numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public Long getIdPersonaNatural() {
		return idPersonaNatural;
	}

	public void setIdPersonaNatural(Long idPersonaNatural) {
		this.idPersonaNatural = idPersonaNatural;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
        
	public Double getTamano() {
		return tamano;
	}

	public void setTamano(Double tamano) {
		this.tamano = tamano;
	}
        
	public Long getIdVivienda() {
		return idVivienda;
	}

	public void setIdVivienda(Long idVivienda) {
		this.idVivienda = idVivienda;
	}	
}
