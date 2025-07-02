package tuti.desi.entidades;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


//GAcosta - Representa mi tabla Familia en la BD 
@Entity
public class Familia {
  
  	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nroFamilia;
   
    private String nombre;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistro;
    
    private Boolean deshabilitado = false;

   @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Asistido> integrantesFamiliaAsistida = new ArrayList<>();

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
	
	//public String getFechaRegistroToString() {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//return sdf.format(fechaRegistro);
	//}
	public List<Asistido> getIntegrantesFamiliaAsistida() {
		return integrantesFamiliaAsistida;
	}
	public void setIntegrantesFamiliaAsistida(List<Asistido> integrantesFamiliaAsistida) {
		this.integrantesFamiliaAsistida = integrantesFamiliaAsistida;
	}

    

}
