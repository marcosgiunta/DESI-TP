package tuti.desi.presentacion.asistido;

import java.sql.Date;
import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Size;

public class AsistidoForm {

	@NotNull
	private Integer dni;
	
	@Size(min=2, message = "El nombre no puede ser vacío")
	private String nombre;
	
	@Size(min=2, message = "El apellido no puede ser vacío")
	private String apellido;
	
	@NotNull
	private String domicilio;
	
	@NotNull
	private String ocupacion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
	
	private Integer familiaId;

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getFamiliaId() {
		return familiaId;
	}

	public void setFamiliaId(Integer familiaId) {
		this.familiaId = familiaId;
	}
	
	
}
