package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.AsistidoRepositorio;
import tuti.desi.accesoDatos.PersonaRepositorio;
import tuti.desi.entidades.Asistido;

@Service
public class AsistidoServiceImpl implements AsistidoService {

    @Autowired
	private AsistidoRepositorio asistidoRepositorio;

    AsistidoServiceImpl(PersonaRepositorio personaRespositorio) {
    }
	
	@Override
	public String SalvarAsistido (Asistido asistido) {
		Asistido asistidoPersona = asistidoRepositorio.findByDni(asistido.getDni());
		    if (asistidoPersona == null) {
		        asistidoRepositorio.save(asistido);
		        return "Se guardó correctamente";
		    } else {
		        return "Existe otra persona con el mismo DNI";
		    }
		
	}

	@Override
	public Asistido findByDni(Integer dni) {
		return asistidoRepositorio.findByDni(dni);
	}
	
	@Override
	public void eliminar(Integer id) {
		asistidoRepositorio.findById(id).ifPresent(asistido ->{
			asistido.setDeshabilitado(true);
			asistidoRepositorio.save(asistido);
			
		});
	}
}
