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
		    AND (:calorias IS NULL OR 
		        (SELECT SUM(i.calorias) FROM ItemReceta i WHERE i.receta = r) >= :calorias)
		""")
		List<Receta> buscarPorFiltros(String nombreReceta, Integer calorias);
		List<Receta> findByEliminadaFalse();
		Optional<Receta> findByNombre(String nombre);
		
		@Query("SELECT r FROM Receta r WHERE r.eliminada = false")
		List<Receta> findByEliminadoFalse();
}
