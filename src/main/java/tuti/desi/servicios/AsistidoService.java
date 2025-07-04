package tuti.desi.servicios;

import java.util.Optional;

import tuti.desi.entidades.Asistido;

public interface AsistidoService {
	
	//Guarda Registro con los datos del asistido
	String SalvarAsistido (Asistido asistido);
	
	//Busca asistido por DNI
	Asistido findByDni(Integer dni);
	
	//Busca asistido por ID
	Optional<Asistido> findById(Integer id);
	
	//Borrado lógico de un asistido
	void eliminar(Integer id);

}
