package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import tuti.desi.entidades.ItemReceta;

public interface ItemRecetaRepositorio extends CrudRepository<ItemReceta, Integer> {

	@Query("SELECT SUM(ir.calorias) FROM ItemReceta ir WHERE ir.receta.id = :recetaId AND ir.eliminado = false")
	Integer obtenerCaloriasTotalesPorReceta(@Param("recetaId") Integer recetaId);

}