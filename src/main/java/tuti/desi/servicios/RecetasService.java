package tuti.desi.servicios;

import java.util.List;
import tuti.desi.entidades.Receta;

public interface RecetasService {
	
	public List<Receta> listarRecetas();
		
	public void guardarReceta(Receta receta);

	public Receta buscarPorId(int id);
		
	public void eliminarReceta(int id);
	
	public void eliminarIngrediente(int idReceta, int index);

	List<Receta> buscarPorFiltros(String nombreReceta, Integer caloriasReceta);
}
