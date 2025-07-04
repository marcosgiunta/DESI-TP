package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tuti.desi.accesoDatos.FamiliaRepositorio;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;

@Service

public class FamiliaServiceImpl implements FamiliaService{

	@Autowired
	private FamiliaRepositorio familiaRepositorio;
	
	@Override
	public List<Familia> listarFamilia() {
	    List<Familia> familias = familiaRepositorio.findByDeshabilitadoFalse();
	    return familias;
	}

	@Override
	public Optional<Familia> buscarPorId(Integer id) {
		return familiaRepositorio.findById(id);
    }
	
	@Override
	@Transactional
	public void salvarFamilia(Familia familia) {
	    // Esto solo se debe imprimir si el ID no es null (es decir, en edición)
	    if (familia.getNroFamilia() != null) {
	        System.out.println("Editando familia existente ID=" + familia.getNroFamilia());
	    } else {
	        System.out.println("Guardando nueva familia...");
	    }

	    familiaRepositorio.save(familia);
	}
	
	@Override
	public void eliminar(Integer id) {
	    Optional<Familia> familia = familiaRepositorio.findById(id);
	    if (familia.isPresent()) {
	        Familia f = familia.get();
	        f.setDeshabilitado(true); // Borrado lógico la familia

	        // Borrado lógico de los integrantes de la familia
	        if (f.getIntegrantesFamiliaAsistida() != null) {
	            for (Asistido asistido : f.getIntegrantesFamiliaAsistida()) {
	                asistido.setDeshabilitado(true); 
	            }
	        }

	        // Guardamos los cambios 
	        familiaRepositorio.save(f);
	    }
	}
	
	@Override
	public List<Familia> buscarPorNombre(String nombre) {
	    return familiaRepositorio.findByNombreContainingIgnoreCaseAndDeshabilitadoFalse(nombre);
	}

	@Override
	public List<Familia> buscarPorNroFamilia(Integer nro) {
	    return familiaRepositorio.findByNroFamiliaAndDeshabilitadoFalse(nro);
	}
	
	
	
}
