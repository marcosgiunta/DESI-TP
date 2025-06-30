package tuti.desi.servicios;

import java.util.List;
import tuti.desi.entidades.Receta;

public interface RecetasService {
	
	public List<Receta> listarRecetas();
		
	public Receta guardarReceta(Receta receta);

	public Receta buscarPorId(int id);
		
	public void eliminarReceta(int id);

	List<Receta> buscarPorFiltros(String nombreReceta);
}
