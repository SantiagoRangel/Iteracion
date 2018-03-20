package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class HotelOfrece {  

    /**
     * @return the idHotelOfrece
     */
    public Long getIdHotelOfrece() {
        return idHotelOfrece;
    }

    /**
     * @param idHotelOfrece the idHotelOfrece to set
     */
    public void setIdHotelOfrece(Long idHotelOfrece) {
        this.idHotelOfrece = idHotelOfrece;
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
    
	@JsonProperty(value="idHotelOfrece")
	private Long idHotelOfrece;
	
	@JsonProperty(value="idHotel")
	private Long idHotel;
	
	@JsonProperty(value="idServicioHotel")
	private Long idServicioHotel;	
	
	public HotelOfrece(@JsonProperty(value="idHotelOfrece")Long idHotelOfrece,
                @JsonProperty(value="idHotel") Long idHotel,
                @JsonProperty(value="idServicioHotel") Long idServicioHotel)
        {
		super();
		this.idHotel = idHotel;
		this.idServicioHotel = idServicioHotel;
		this.idHotelOfrece = idHotelOfrece;
	}
        
          
}

