package tuti.desi.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.AsistidoRepositorio;
import tuti.desi.accesoDatos.PersonaRepositorio;
import tuti.desi.entidades.Asistido;

@Service
public class AsistidoServiceImpl implements AsistidoService {

    @Autowired
	private AsistidoRepositorio asistidoRepositorio;

//    AsistidoServiceImpl(PersonaRepositorio personaRespositorio) {
//    }
	
	//Guarda el asistido si no existe otro registro con el mismo DNI, sino informa
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
    public Optional<Asistido> findById(Integer id) {
        return asistidoRepositorio.findById(id);
    }
	
	@Override
	public void eliminar(Integer id) {
	    Asistido asistido = asistidoRepositorio.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Asistido no encontrado: " + id));

	    asistido.setDeshabilitado(true); // eliminado lógico
	    asistidoRepositorio.save(asistido); // guardar cambio
	}
}
