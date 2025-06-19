package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Familia {
  
    @Id
    private int nroFamilia;
    private String nombre;
    private Date fechaRegistro;

    public int getNroFamilia() {
        return nroFamilia;
    }
    public void setNroFamilia(int nroFamilia) {
        this.nroFamilia = nroFamilia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    

}
