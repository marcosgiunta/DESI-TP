package tuti.desi.presentacion.familia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import tuti.desi.presentacion.asistido.AsistidoForm;

public class FamiliaForm {
	
	private Integer nroFamilia;
	
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
	private String nombre;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;

	private Boolean deshabilitado = false;;

	@Valid
	private List<AsistidoForm> integrantes = new ArrayList<>();
	
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

	public void setDeshabilitado(Boolean deshabilitado) {
		this.deshabilitado = deshabilitado;
	}

	public Integer getNroFamilia() {
		return nroFamilia;
	}

	public void setNroFamilia(Integer nroFamilia) {
		this.nroFamilia = nroFamilia;
	}

	public List<AsistidoForm> getIntegrantes() {
	    if (integrantes == null) {
	        integrantes = new ArrayList<>();
	    }
	    return integrantes;
	}

	public void setIntegrantes(List<AsistidoForm> integrantes) {
		this.integrantes = integrantes;
	}
	
	
		
}
