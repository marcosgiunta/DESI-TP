package tuti.desi.entidades;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


//Esta clase representa a la Familia y la lista de integrantes
@Entity
public class Familia {
  
  	//Número de familia es el ID de la tabla
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nroFamilia;

    //Nombre con que se reconoce la familia (alias)
  	private String nombre;
    
  	//Fecha en que se regista la familia
  	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
    
    //Se utiliza para el borrado lógico
    private Boolean deshabilitado = false;

    //Lista de integrantes de la familia
   @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Asistido> asistidos = new ArrayList<>();

      
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
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
  public void setFechaRegistro(LocalDate fechaRegistro) {
     this.fechaRegistro = fechaRegistro;
  }

	public Boolean getDeshabilitado() {
		return deshabilitado;
	}
	public void setDeshabilitado(Boolean estaDeshabilitad) {
		deshabilitado = estaDeshabilitad;
	}
	
	public List<Asistido> getIntegrantesFamiliaAsistida() {
		return asistidos;
	}
	public void setIntegrantesFamiliaAsistida(List<Asistido> integrantesFamiliaAsistida) {
		this.asistidos = integrantesFamiliaAsistida;
	}


}
