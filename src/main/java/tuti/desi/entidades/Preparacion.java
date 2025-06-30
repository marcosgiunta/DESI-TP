package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;


@Entity
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date FechaPreparacion;
    private Integer stockRacionesRestantes;
    private Integer TotalRacionesPreparadas;


    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

public Receta getReceta() {
        return receta;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getFechaPreparacion() {
        return FechaPreparacion;
    }
    public void setFechaPreparacion(Date fechaPreparacion) {
        FechaPreparacion = fechaPreparacion;
    }
    public Integer getStockRacionesRestantes() {
        return stockRacionesRestantes;
    }
    public void setStockRacionesRestantes(Integer stockRacionesRestantes) {
        this.stockRacionesRestantes = stockRacionesRestantes;
    }
    public Integer getTotalRacionesPreparadas() {
        return TotalRacionesPreparadas;
    }
    public void setTotalRacionesPreparadas(Integer totalRacionesPreparadas) {
        TotalRacionesPreparadas = totalRacionesPreparadas;
    }



}
