package tuti.desi.entidades;

import java.time.LocalDate;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Subselect("SELECT nrofamilia, ultimafecha FROM ultimafechaentregafamilia")
@Table(name = "ultimafechaentregafamilia") 
public class vistaUltimaEntega {

    @Id
    @Column(name = "nrofamilia")
    private Integer nroFamilia;

    @Column(name = "ultimafecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ultimaFecha;

    public Integer getNroFamilia() {
        return nroFamilia;
    }

    public void setNroFamilia(Integer nroFamilia) {
        this.nroFamilia = nroFamilia;
    }

    public LocalDate getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(LocalDate ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }
}
