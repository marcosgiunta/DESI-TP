package tuti.desi.servicios;

import java.util.Optional;

import tuti.desi.entidades.Asistido;

public interface AsistidoService {
	
	String SalvarAsistido (Asistido asistido);
	
	Asistido findByDni(Integer dni);
	
	Optional<Asistido> findById(Integer id);
	
	void eliminar(Integer id);

}
