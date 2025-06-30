package tuti.desi.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Familia;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Integer> {

	List<Familia> findByDeshabilitadoFalse();
	
	List<Familia> findByNombreContainingIgnoreCaseAndDeshabilitadoFalse(String nombre);
	
	List<Familia> findByNroFamiliaAndDeshabilitadoFalse(Integer nroFamilia);

	List<Familia> findByNombreContainingIgnoreCase(String nombre);

	List<Familia> findByNroFamilia(Integer nroFamilia);
	
}
