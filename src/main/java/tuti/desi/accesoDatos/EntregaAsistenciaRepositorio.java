package tuti.desi.accesoDatos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.EntregaAsistencia;
import tuti.desi.entidades.Familia;

@Repository
public interface EntregaAsistenciaRepositorio extends JpaRepository<EntregaAsistencia, Integer>{


 // Método para buscar entregas de asistencia por fecha, número de familia y nombre de familia
 // Si el parámetro es null, no se aplica filtro para ese campo
@Query("SELECT e FROM EntregaAsistencia e " +
       "WHERE (:fecha IS NULL OR e.fecha = :fecha) " +
       "AND (:nroFamilia IS NULL OR e.familia.nroFamilia = :nroFamilia) " +
       "AND (:nombreFamilia IS NULL OR LOWER(e.familia.nombre) LIKE LOWER(CONCAT('%', :nombreFamilia, '%')))")
List<EntregaAsistencia> buscarPorFiltros(LocalDate fecha,Integer nroFamilia,String nombreFamilia);


 // Método para buscar una entrega de asistencia por familia y fecha
 // Devuelve null si no se encuentra ninguna entrega
@Query("SELECT e FROM EntregaAsistencia e WHERE e.familia = :familia AND e.fecha = :fecha")
EntregaAsistencia findByFamiliaAndFecha(Familia familia, LocalDate fecha);

}
