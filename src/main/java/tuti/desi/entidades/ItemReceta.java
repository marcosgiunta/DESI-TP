package tuti.desi.entidades;
import jakarta.persistence.*;

@Entity
public class ItemReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cantidad;
    private Integer calorias;

    @ManyToOne
    @JoinColumn(name = "ingredientes_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Integer getCalorias() {
        return calorias;
    }
    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public Ingrediente getIngrediente() {
    return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

}
