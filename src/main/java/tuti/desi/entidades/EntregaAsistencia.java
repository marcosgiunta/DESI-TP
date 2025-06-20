package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class EntregaAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha;
    private int cantidadRaciones;

    @ManyToOne
    @JoinColumn(name = "preparacion_id")
    private Preparacion preparacion;

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;

    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    public int getId() {
        return id;
    }   
    public void setId(int id) {
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
