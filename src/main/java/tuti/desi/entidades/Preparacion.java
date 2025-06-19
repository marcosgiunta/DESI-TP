package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.Date;


@Entity
public class Preparacion {

    @Id
    private String id;
    private Date FechaPreparacion;
    private int stockRacionesRestantes;
    private int TotalRacionesPreparadas;


    public String getId() {
        return id;
    }
    public void setId(String id) {
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
