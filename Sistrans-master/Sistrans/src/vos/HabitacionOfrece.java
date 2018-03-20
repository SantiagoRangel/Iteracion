package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class HabitacionOfrece {  
    
	@JsonProperty(value="idHabitacionOfrece")
	private String idHabitacionOfrece;
	
	@JsonProperty(value="idHabitacion")
	private String idHabitacion;
	
	@JsonProperty(value="idServicioHabitacion")
	private Long idServicioHabitacion;	
	
	public HabitacionOfrece(@JsonProperty(value="idHabitacionOfrece")String idHabitacionOfrece,
                @JsonProperty(value="idHabitacion") String idHabitacion,
                @JsonProperty(value="idServicioHabitacion") Long idServicioHabitacion) 
        {
		super();
		this.idHabitacion = idHabitacion;
		this.idServicioHabitacion = idServicioHabitacion;
		this.idHabitacionOfrece = idHabitacionOfrece;
	}
        
          /**
     * @return the idHabitacionOfrece
     */
    public String getIdHabitacionOfrece() {
        return idHabitacionOfrece;
    }

    /**
     * @param idHabitacionOfrece the idHabitacionOfrece to set
     */
    public void setIdHabitacionOfrece(String idHabitacionOfrece) {
        this.idHabitacionOfrece = idHabitacionOfrece;
    }

    /**
     * @return the idHabitacion
     */
    public String getIdHabitacion() {
        return idHabitacion;
    }

    /**
     * @param idHabitacion the idHabitacion to set
     */
    public void setIdHabitacion(String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    /**
     * @return the idServicioHabitacion
     */
    public Long getIdServicioHabitacion() {
        return idServicioHabitacion;
    }

    /**
     * @param idServicioHabitacion the idServicioHabitacion to set
     */
    public void setIdServicioHabitacion(Long idServicioHabitacion) {
        this.idServicioHabitacion = idServicioHabitacion;
    }
}

