package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class EntregaAsistencia {

    @Id
    private String id;
    private Date fecha;
    private int cantidadRaciones;

    public String getId() {
        return id;
    }   
    public void setId(String id) {
        this.id = id;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getCantidadRaciones() {
        return cantidadRaciones;
    }
    public void setCantidadRaciones(int cantidadRaciones) {
        this.cantidadRaciones = cantidadRaciones;
    }
 

    
}
