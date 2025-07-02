package tuti.desi.accesoDatos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Receta;

@Repository
public interface RecetasRepositorio extends JpaRepository<Receta, Integer>{

@Query("SELECT e FROM EntregaAsistencia e " +
       "WHERE (:fecha IS NULL OR e.fecha = :fecha) " +
       "AND (:nroFamilia IS NULL OR e.familia.nroFamilia = :nroFamilia) " +
       "AND (:nombreFamilia IS NULL OR LOWER(e.familia.nombre) LIKE LOWER(CONCAT('%', :nombreFamilia, '%')))")
List<Receta> buscarPorFiltros(String nombreReceta);

}
