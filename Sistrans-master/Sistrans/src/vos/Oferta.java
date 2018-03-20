package vos;

import java.sql.Date;
import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta{   
	
	@JsonProperty(value="fechaFinal")
	private java.util.Date fechaFinal;	
        
        @JsonProperty(value="fechaInicial")
	private java.util.Date fechaInicial;
        
        @JsonProperty(value="idOferta")
	private Long idOferta;
        
        @JsonProperty(value="idHabitacion")
	private Long idHabitacion;
         
        @JsonProperty(value="idApartamento")
	private Long idApartamento;
	
        @JsonProperty(value="idVivienda")
	private Long idVivienda;
         
	public Oferta(@JsonProperty(value="idVivienda") Long idVivienda,
			@JsonProperty(value="idApartamento")Long idApartamento,
                        @JsonProperty(value="idHabitacion") Long idHabitacion,
                        @JsonProperty(value="idOferta") Long idOferta,
                        @JsonProperty(value="fechaInicial") java.util.Date fechaInicial,
                        @JsonProperty(value="fechaFinal") java.util.Date fechaFinal) 
        {
		super();
		this.idVivienda=idVivienda;
                this.idOferta=idOferta;
                this.idHabitacion=idHabitacion;
                this.idApartamento=idApartamento;
                this.fechaInicial=fechaInicial;
                this.fechaFinal=fechaFinal;
	}
        
         /**
     * @return the fechaFinal
     */
    public java.util.Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(java.util.Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the fechaInicial
     */
    public java.util.Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(java.util.Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the idOferta
     */
    public Long getIdOferta() {
        return idOferta;
    }

    /**
     * @param idOferta the idOferta to set
     */
    public void setIdOferta(Long idOferta) {
        this.idOferta = idOferta;
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
     * @return the idApartamento
     */
    public Long getIdApartamento() {
        return idApartamento;
    }

    /**
     * @param idApartamento the idApartamento to set
     */
    public void setIdApartamento(Long idApartamento) {
        this.idApartamento = idApartamento;
    }

    /**
     * @return the idVivienda
     */
    public Long getIdVivienda() {
        return idVivienda;
    }

    /**
     * @param idVivienda the idVivienda to set
     */
    public void setIdVivienda(Long idVivienda) {
        this.idVivienda = idVivienda;
    }
}