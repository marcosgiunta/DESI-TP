package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;


@Entity
public class Preparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date FechaPreparacion;
    private int stockRacionesRestantes;
    private int TotalRacionesPreparadas;


    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

public Receta getReceta() {
        return receta;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getFechaPreparacion() {
        return FechaPreparacion;
    }
    public void setFechaPreparacion(Date fechaPreparacion) {
        FechaPreparacion = fechaPreparacion;
    }
    public int getStockRacionesRestantes() {
        return stockRacionesRestantes;
    }
    public void setStockRacionesRestantes(int stockRacionesRestantes) {
        this.stockRacionesRestantes = stockRacionesRestantes;
    }
    public int getTotalRacionesPreparadas() {
        return TotalRacionesPreparadas;
    }
    public void setTotalRacionesPreparadas(int totalRacionesPreparadas) {
        TotalRacionesPreparadas = totalRacionesPreparadas;
    }



}
