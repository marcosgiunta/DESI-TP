package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import tuti.desi.entidades.Familia;

public interface FamiliaService {

	//Lista los registros de las Familias
		List<Familia> listarFamilia();
		
		//Busca registros de familias
		Optional<Familia> buscarPorId(Integer id);
		
		
		//Guarda el registro de familia
		void salvarFamilia(Familia familia);
		
		//Eliminación lógica de familia
		void eliminar(Integer id);
		
		List<Familia> buscarPorNombre(String nombre);
		
		List<Familia> buscarPorNroFamilia(Integer nro);
		
		
		
}
