package tuti.desi.entidades;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class EntregaAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha;
    private Integer cantidadRaciones;

    @ManyToOne
    @JoinColumn(name = "preparacion_id")
    private Preparacion preparacion;

    @ManyToOne
    @JoinColumn(name = "familia_id")
    private Familia familia;

    //voluntario id en la base de datos aparece como voluntario_id = null 
    //esto sucede porque para el TP no se pidio en las historias de usuario
    //que se guarde el voluntario que realiza la entrega, por lo tanto no se guarda
    //pero se deja el campo para que en un futuro se pueda implementar 
    @ManyToOne
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    public Preparacion getPreparacion() {
        return preparacion;
    }
    public void setPreparacion(Preparacion preparacion) {
        this.preparacion = preparacion;
    }
    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Integer getId() {
        return id;
    }   
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public Integer getCantidadRaciones() {
        return cantidadRaciones;
    }
    public void setCantidadRaciones(Integer cantidadRaciones) {
        this.cantidadRaciones = cantidadRaciones;
    }
 

    
}