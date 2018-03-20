package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Apartamento {
    
	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	@JsonProperty(value="precio")
	private Long precio;
	
	@JsonProperty(value="idApartamento")
	private Long idApartamento;
	
	@JsonProperty(value="tamano")
	private Long tamano;
	
	@JsonProperty(value="idPersonaNatural")
	private Long idPersonaNatural;
	
	public Apartamento(@JsonProperty(value="ubicacion")String ubicacion,
                @JsonProperty(value="descripcion") String descripcion,
                @JsonProperty(value="precio") Long precio,
	        @JsonProperty(value="idApartamento") Long idApartamento, 
                @JsonProperty(value="tamano") Long tamano,
                @JsonProperty(value="idPersonaNatural") Long idPersonaNatural)
        {
		super();
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.idApartamento = idApartamento;
		this.tamano = tamano;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
        
          /**
     * @return the idPersonaNatural
     */
    public Long getIdPersonaNatural() {
        return idPersonaNatural;
    }

    /**
     * @param idPersonaNatural the idPersonaNatural to set
     */
    public void setIdPersonaNatural(Long idPersonaNatural) {
        this.idPersonaNatural = idPersonaNatural;
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

	public Long getIdApartamento() {
		return idApartamento;
	}

	public void setIdApartamento(Long idApartamento) {
		this.idApartamento = idApartamento;
	}

	public Long getTamano() {
		return tamano;
	}

	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}
}
