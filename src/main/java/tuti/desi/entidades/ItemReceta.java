package tuti.desi.entidades;
import jakarta.persistence.*;

@Entity
public class ItemReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private int calorias;

    @ManyToOne
    @JoinColumn(name = "ingredientes_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
