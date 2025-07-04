package tuti.desi.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tuti.desi.accesoDatos.RecetasRepositorio;
import tuti.desi.entidades.Receta;

@Service
public class RecetasServiceImpl implements RecetasService {

	@Autowired
	private RecetasRepositorio repositorio;

	@Override
	public List<Receta> listarRecetas() {
	    return repositorio.findByEliminadaFalse();
	}

	@Override
	public void guardarReceta(Receta receta) {
	    Optional<Receta> existente = repositorio.findByNombre(receta.getNombre());

	    if (existente.isPresent() && !existente.get().getId().equals(receta.getId())) {
	        throw new IllegalArgumentException("Ya existe una receta con ese nombre");
	    }

	    repositorio.save(receta);
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
	public List<Receta> buscarPorFiltros(String nombreReceta, Integer caloriasReceta) {
		return repositorio.buscarPorFiltros(nombreReceta, caloriasReceta);
	}
	
	@Transactional
    public void eliminarIngrediente(int idReceta, int index) {
        Receta receta = repositorio.findById(idReceta).orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        if (index >= 0 && index < receta.getIngredientes().size()) {
            receta.getIngredientes().remove(index);

            repositorio.save(receta);
            throw new IllegalArgumentException("Índice de ingrediente inválido");
        }
    }

}
