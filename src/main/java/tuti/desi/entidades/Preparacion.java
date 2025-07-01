package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaPreparacion;

    private Integer stockRacionesRestantes;
    private Integer totalRacionesPreparadas = 0;

    @Column(nullable = false)
    private boolean eliminado = false;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    // Getters y setters...

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Date getFechaPreparacion() { return fechaPreparacion; }
    public void setFechaPreparacion(Date fechaPreparacion) { this.fechaPreparacion = fechaPreparacion; }

    public Integer getStockRacionesRestantes() { return stockRacionesRestantes; }
    public void setStockRacionesRestantes(Integer stockRacionesRestantes) { this.stockRacionesRestantes = stockRacionesRestantes; }

    public Integer getTotalRacionesPreparadas() { return totalRacionesPreparadas; }
    public void setTotalRacionesPreparadas(Integer totalRacionesPreparadas) { this.totalRacionesPreparadas = totalRacionesPreparadas; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }

    public boolean isEliminado() { return eliminado; }
    public void setEliminado(boolean eliminado) { this.eliminado = eliminado; }
}
