package tuti.desi.entidades;
import java.util.Date;

import jakarta.validation.constraints.NotNull;


import jakarta.persistence.*;  

/*
 * Esta clase representa a un Asistido - hereda de Persona)
 */

@Entity
public class Asistido  extends Persona {

	//Fecha en que se registra al asistido
	@NotNull
    private Date fechaRegistro;

    //Nro de la Familia a la que pertenece el Asistido (ID de Familia)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "familia_id") 
    private Familia familia;

    //Este campo se utiliza para el borrado lógico
	private Boolean deshabilitado = false;
    
	public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getDeshabilitado() {
		return deshabilitado;
	}
    
	public void setDeshabilitado(Boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}
	
	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

}
