package tuti.desi.presentacion.familia;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import tuti.desi.presentacion.asistido.AsistidoForm;

public class FamiliaForm {
	
	private Integer nroFamilia;
	
	@Size(min=2, message="Completar el nombre de la Familia")
	private String nombre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRegistro;

	private Boolean deshabilitado;

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
		return integrantes;
	}

	public void setIntegrantes(List<AsistidoForm> integrantes) {
		this.integrantes = integrantes;
	}
	
	
		
}
