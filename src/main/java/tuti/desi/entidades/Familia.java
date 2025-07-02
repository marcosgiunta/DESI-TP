package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
public class Familia {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nroFamilia;
    private String nombre;
    private Date fechaRegistro;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nro_familia_fk", referencedColumnName = "nroFamilia")
    private List<Asistido> integrantesFamiliaAsistida;

    public List<Asistido> getIntegrantesFamiliaAsistida() {
        return integrantesFamiliaAsistida;
    }

    public Integer getNroFamilia() {
        return nroFamilia;
    }
    public void setNroFamilia(Integer nroFamilia) {
        this.nroFamilia = nroFamilia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
  

}
