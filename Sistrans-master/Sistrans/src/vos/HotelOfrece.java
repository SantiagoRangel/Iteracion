package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class HotelOfrece {  
    
	@JsonProperty(value="idHotelOfrece")
	private String idHotelOfrece;
	
	@JsonProperty(value="idHotel")
	private String idHotel;
	
	@JsonProperty(value="idServicioHotel")
	private Long idServicioHotel;	
	
	public HotelOfrece(@JsonProperty(value="idHotelOfrece")String idHotelOfrece,
                @JsonProperty(value="idHotel") String idHotel,
                @JsonProperty(value="idServicioHotel") Long idServicioHotel)
        {
		super();
		this.idHotel = idHotel;
		this.idServicioHotel = idServicioHotel;
		this.idHotelOfrece = idHotelOfrece;
	}
        
          /**
     * @return the idHotelOfrece
     */
    public String getIdHotelOfrece() {
        return idHotelOfrece;
    }

    /**
     * @param idHotelOfrece the idHotelOfrece to set
     */
    public void setIdHotelOfrece(String idHotelOfrece) {
        this.idHotelOfrece = idHotelOfrece;
    }

    /**
     * @return the idHotel
     */
    public String getIdHotel() {
        return idHotel;
    }

    /**
     * @param idHotel the idHotel to set
     */
    public void setIdHotel(String idHotel) {
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

