package tuti.desi.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.RecetasRepositorio;
import tuti.desi.entidades.Receta;

@Service
public class RecetasServiceImpl implements RecetasService {

	@Autowired
	private RecetasRepositorio repositorio;

	@Override
	public List<Receta> listarRecetas() {
		return repositorio.findAll();
	}

	@Override
	public Receta guardarReceta(Receta receta) {
		return repositorio.save(receta);
	}

	@Override
	public Receta buscarPorId(int id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void eliminarReceta(int id) {
		repositorio.deleteById(id);
	}

	@Override
	public List<Receta> buscarPorFiltros(String nombreReceta) {
		return repositorio.buscarPorFiltros(nombreReceta);
	}

}
