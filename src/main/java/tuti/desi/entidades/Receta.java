package tuti.desi.entidades;

import jakarta.persistence.*;

@Entity
public class Receta {

    @Id
    private String id;
    private String nombre;
    private String descripcion;

    public String getId() {
        return id;
    }   
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }




}
