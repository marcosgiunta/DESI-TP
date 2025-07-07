package tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Receta;

@Repository
public interface RecetasRepositorio extends JpaRepository<Receta, Integer>{

	@Query("""
		    SELECT r FROM Receta r 
		    WHERE r.eliminada = false
		    AND (:nombreReceta IS NULL OR LOWER(r.nombre) LIKE LOWER(CONCAT('%', :nombreReceta, '%')))
		    AND (:caloriasMin IS NULL OR 
		         (SELECT SUM(i.calorias) FROM ItemReceta i WHERE i.receta = r AND i.eliminado = false) >= :caloriasMin)
		    AND (:caloriasMax IS NULL OR 
		         (SELECT SUM(i.calorias) FROM ItemReceta i WHERE i.receta = r AND i.eliminado = false) <= :caloriasMax)
		""")
		List<Receta> buscarPorFiltros(String nombreReceta, Integer caloriasMin, Integer caloriasMax);
		List<Receta> findByEliminadaFalse();
		Optional<Receta> findByNombre(String nombre);

		boolean existsByNombreAndIdNotAndEliminadaFalse(String nombre, Integer id);
}
