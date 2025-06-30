package tuti.desi.entidades;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.persistence.*;  

//GAcosta - Representa mi tabla Asistido en la BD
@Entity
public class Asistido  extends Persona {

	@Column(name = "fecha_registro")
    private Date fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "familia_id") 
    private Familia familia;

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public String getFechaRegistroToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fechaRegistro);
		
	}
}
