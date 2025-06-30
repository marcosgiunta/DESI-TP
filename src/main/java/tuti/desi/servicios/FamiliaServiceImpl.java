package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.FamiliaRepositorio;
import tuti.desi.entidades.Familia;

@Service
public class FamiliaServiceImpl implements FamiliaService{

	@Autowired
	private FamiliaRepositorio familiaRepositorio;
	
	@Override
	public List<Familia> listarFamilia() {
		return familiaRepositorio.findByDeshabilitadoFalse();
	}

	@Override
	public Optional<Familia> buscarPorId(Integer id) {
		return familiaRepositorio.findById(id);
    }
	
	@Override
	public void salvarFamilia(Familia familia) {
		familiaRepositorio.save(familia);
	}
	
	@Override
	public void eliminar(Integer id) {
		familiaRepositorio.findById(id).ifPresent(familia ->{
			familia.setDeshabilitado(true);
			familiaRepositorio.save(familia);
		});
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
