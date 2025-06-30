package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Preparacion;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PreparacionRepositorio extends JpaRepository<Preparacion, Long> {

    //boolean existsByRecetaIdAndDiaPreparacion(Long recetaId, LocalDate diaPreparacion);

    List<Preparacion> findByRecetaId(Long recetaId);
}
