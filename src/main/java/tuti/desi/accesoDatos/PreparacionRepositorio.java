package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Preparacion;

import java.util.List;

@Repository
public interface PreparacionRepositorio extends JpaRepository<Preparacion, Integer> {

	List<Preparacion> findByRecetaId(Integer recetaId);

}