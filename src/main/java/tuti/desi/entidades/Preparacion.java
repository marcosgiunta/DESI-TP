package tuti.desi.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaPreparacion;

    @Column(nullable = false)
    private int totalRacionesPreparadas;

    @Column(nullable = false)
    private boolean eliminado = false;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    // Constructor vacío
    public Preparacion() {}

    // Constructor completo
    public Preparacion(LocalDate fechaPreparacion, int totalRacionesPreparadas, Receta receta) {
        this.fechaPreparacion = fechaPreparacion;
        this.totalRacionesPreparadas = totalRacionesPreparadas;
        this.receta = receta;
        this.eliminado = false;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(LocalDate fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public int getTotalRacionesPreparadas() {
        return totalRacionesPreparadas;
    }

    public void setTotalRacionesPreparadas(int totalRacionesPreparadas) {
        this.totalRacionesPreparadas = totalRacionesPreparadas;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public String toString() {
        return "Preparacion{" +
                "id=" + id +
                ", fechaPreparacion=" + fechaPreparacion +
                ", totalRacionesPreparadas=" + totalRacionesPreparadas +
                ", receta=" + receta +
                ", eliminado=" + eliminado +
                '}';
    }
}
