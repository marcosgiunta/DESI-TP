package tuti.desi.presentacion.asistido;

import java.sql.Date;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Size;

public class AsistidoForm {

	@NotNull
	private Integer dni;
	
	@Size(min=2, message = "El nombre no puede ser vacío")
	private String nombre;
	
	private String apellido;
	private String domicilio;
	private String ocupacion;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getFamiliaId() {
		return familiaId;
	}

	public void setFamiliaId(Integer familiaId) {
		this.familiaId = familiaId;
	}
	
	
}
