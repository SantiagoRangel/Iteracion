package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class HotelOfrece {  
    
	@JsonProperty(value="idHotel")
	private Long idHotel;
	
	@JsonProperty(value="idServicioHotel")
	private Long idServicioHotel;	
	
	public HotelOfrece(@JsonProperty(value="idHotel") Long idHotel,
                @JsonProperty(value="idServicioHotel") Long idServicioHotel)
        {
		super();
		this.idHotel = idHotel;
		this.idServicioHotel = idServicioHotel;
	}
        
          /**
     * @return the idHotel
     */
    public Long getIdHotel() {
        return idHotel;
    }

    /**
     * @param idHotel the idHotel to set
     */
    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    /**
     * @return the idServicioHotel
     */
    public Long getIdServicioHotel() {
        return idServicioHotel;
    }

    /**
     * @param idServicioHotel the idServicioHotel to set
     */
    public void setIdServicioHotel(Long idServicioHotel) {
        this.idServicioHotel = idServicioHotel;
    }
}

