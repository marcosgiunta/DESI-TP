package tuti.desi.entidades;
import jakarta.persistence.*;

@Entity
public class ItemReceta {

    @Id
    private String id;
    private int cantidad;
    private int calorias;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getCalorias() {
        return calorias;
    }
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }



}
