package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import tuti.desi.entidades.Familia;

public interface FamiliaService {

		//Lista los registros de las Familias
		List<Familia> listarFamilia();
		
		//Busca registros de familias por ID
		Optional<Familia> buscarPorId(Integer id);
		
		
		//Guarda/actualiza el registro de familia
		void salvarFamilia(Familia familia);
		
		//Eliminación lógica de familia
		void eliminar(Integer id);
		
		//Busca Familias por Nombre
		List<Familia> buscarPorNombre(String nombre);
		
		//Busca Familias por Nro (ID)
		List<Familia> buscarPorNroFamilia(Integer nro);
		


}
