package tuti.desi.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.RecetasRepositorio;
import tuti.desi.entidades.Recetas;

@Service
public class RecetasServiceImpl implements RecetaService {

	@Autowired
	private RecetasRepositorio repositorio;

	@Override
	public List<Recetas> ListarRecetas() {
		return repositorio.findAll();
	}

	@Override
	public Recetas guardarEntrega(Recetas recetas) {
		return repositorio.save(recetas);
	}

	@Override
	public Recetas buscarPorId(int id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void eliminarReceta(int id) {
		repositorio.deleteById(id);
	}

	@Override
	public List<Recetas> buscarPorFiltros(String nombreReceta) {
		return repositorio.buscarPorFiltros(nombreReceta);
	}

}
