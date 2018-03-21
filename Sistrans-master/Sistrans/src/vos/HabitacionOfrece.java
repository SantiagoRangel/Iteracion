package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class HabitacionOfrece {  
	
	@JsonProperty(value="idHabitacion")
	private Long idHabitacion;
	
	@JsonProperty(value="idServicioHabitacion")
	private Long idServicioHabitacion;	
	
	public HabitacionOfrece(@JsonProperty(value="idHabitacion") Long idHabitacion,
                @JsonProperty(value="idServicioHabitacion") Long idServicioHabitacion) 
        {
		super();
		this.idHabitacion = idHabitacion;
		this.idServicioHabitacion = idServicioHabitacion;
	}
        
    /**
     * @return the idHabitacion
     */
    public Long getIdHabitacion() {
        return idHabitacion;
    }

    /**
     * @param idHabitacion the idHabitacion to set
     */
    public void setIdHabitacion(Long idHabitacion) {
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

