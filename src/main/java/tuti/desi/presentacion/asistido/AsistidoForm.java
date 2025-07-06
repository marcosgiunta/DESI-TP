package tuti.desi.presentacion.asistido;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class AsistidoForm {

	@NotNull
	@Min(value = 1000000, message = "El DNI al menos debe tener 7 cifras")
	private Integer dni;
	
	@NotNull
	@Size(min=2, message = "El nombre debe tener al menos 2 caracteres")
	private String nombre;
	
	@NotNull
	@Size(min=2, message = "El apellido debe tener al menos 2 caracteres")
	private String apellido;
	
	@NotNull
	@Size(min=2, message = "El domicilio debe tener al menos 2 caracteres")
	private String domicilio;
	
	@NotNull
	private String ocupacion;
	
	@NotNull
	@Past(message = "La fecha no ser puede posterior a la actual")
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
