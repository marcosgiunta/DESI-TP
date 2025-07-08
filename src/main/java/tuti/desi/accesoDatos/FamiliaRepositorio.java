package tuti.desi.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Familia;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Integer> {

	//Trae todas las familias que no estan deshabilitadas
	List<Familia> findByDeshabilitadoFalse();
	
	//Trae todas las familias que no estan deshabilitadas por parte del nombre
	List<Familia> findByNombreContainingIgnoreCaseAndDeshabilitadoFalse(String nombre);
	
	//Trae la familia por nro de familia si no esta deshabilitada
	List<Familia> findByNroFamiliaAndDeshabilitadoFalse(Integer nroFamilia);

  // Método para buscar una familia por su número
  // Devuelve una lista de números de familia que estan habilitadas,es decir, familias que no fueron eliminadas
  @Query("SELECT f.nroFamilia FROM Familia f where f.deshabilitado = false")
  List<Integer> findAllNroFamilia();

  Familia findByNroFamilia(Integer nroFamilia);

	
		
}

