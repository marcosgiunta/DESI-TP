package tuti.desi.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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

		
}
