package tuti.desi.servicios;

import java.util.List;
import tuti.desi.entidades.Recetas;

public interface EntregaAsistenciaService {
	
	public List<Recetas> ListarRecetas();
		
	public Recetas guardarEntrega(Recetas receta);

	public Recetas buscarPorId(int id);
		
	public void eliminarReceta(int id);

	List<Recetas> buscarPorFiltros(String nombreReceta);
}
