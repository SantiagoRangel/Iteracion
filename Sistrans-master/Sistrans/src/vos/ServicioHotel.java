package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ServicioHotel {

	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="idServicioHotel")
	private Long idServicioHotel;
        
	@JsonProperty(value="tipo")
	private String tipo;
	
	public ServicioHotel(@JsonProperty(value="tipo")String tipo,
			@JsonProperty(value="descripcion")String descripcion,
			@JsonProperty(value="idServicioHotel") Long idServicioHotel) 
        {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.idServicioHotel = idServicioHotel;
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

	public Long getIdServicioHotel() {
		return idServicioHotel;
	}

	public void setIdServicioHotel(Long idServicioHotel) {
		this.idServicioHotel = idServicioHotel;
	}
}
