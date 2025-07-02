package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Preparacion;

import java.util.Date;
import java.util.List;

@Repository
public interface PreparacionRepositorio extends JpaRepository<Preparacion, Integer> {

	List<Preparacion> findByEliminadoFalse();


	@Query("SELECT p FROM Preparacion p " +
       "WHERE (:fecha IS NULL OR p.fechaPreparacion = :fecha) " +
       "AND (:nombreReceta IS NULL OR LOWER(p.receta.nombre) LIKE LOWER(CONCAT('%', :nombreReceta, '%')))")
List<Preparacion> buscarPorFiltros(Date fecha,String nombreReceta);


}