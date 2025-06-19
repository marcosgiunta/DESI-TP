package tuti.desi.entidades;
import java.util.Date;
import jakarta.persistence.Entity;  

@Entity
public class Asistido  extends Persona {

    private Date fechaRegistro;

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
