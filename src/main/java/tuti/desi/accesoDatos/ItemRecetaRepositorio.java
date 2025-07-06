package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import tuti.desi.entidades.ItemReceta;

public interface ItemRecetaRepositorio extends CrudRepository<ItemReceta, Integer> {

	@Query(value = "SELECT SUM(calorias) FROM item_receta WHERE receta_id = :recetaId AND eliminado = 0", nativeQuery = true)
	Integer obtenerCaloriasTotalesPorReceta(@Param("recetaId") Integer recetaId);

}