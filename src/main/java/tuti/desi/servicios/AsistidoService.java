package tuti.desi.servicios;

import tuti.desi.entidades.Asistido;

public interface AsistidoService {
	
	String SalvarAsistido (Asistido asistido);
	
	Asistido findByDni(Integer dni);
	
	void eliminar(Integer id);

}
