package tuti.desi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.RecetasRepositorio;
import jakarta.transaction.Transactional;
import tuti.desi.entidades.ItemReceta;
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
        if (repositorio.existsByNombreAndIdNot(receta.getNombre(), receta.getId())) {
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
	public List<Receta> buscarPorFiltros(String nombreReceta, Integer caloriasMin, Integer caloriasMax) {
		return repositorio.buscarPorFiltros(nombreReceta, caloriasMin, caloriasMax);
	}
	
	@Transactional
    public void eliminarIngrediente(int idReceta, int index) {
        Receta receta = repositorio.findById(idReceta).orElseThrow(() -> new RuntimeException("Receta no encontrada"));

        List<ItemReceta> ingredientes = receta.getIngredientes();
        if (index >= 0 && index < receta.getIngredientes().size()) {
        	ingredientes.get(index).setEliminado(true);
            repositorio.save(receta);
        } else {
            throw new IllegalArgumentException("Índice de ingrediente inválido");
        }
    }

}
