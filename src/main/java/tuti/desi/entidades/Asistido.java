package tuti.desi.entidades;
import java.util.Date;
import jakarta.persistence.*;  

@Entity
public class Asistido  extends Persona {

    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "familia_id") 
    private Familia familia;

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
