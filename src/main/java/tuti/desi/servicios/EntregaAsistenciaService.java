package tuti.desi.servicios;

import java.util.List;

import tuti.desi.entidades.EntregaAsistencia;

public interface EntregaAsistenciaService {
	
	public List<EntregaAsistencia> ListarEntregaAsistencia();
		
	public EntregaAsistencia guardarEntrega(EntregaAsistencia entrega);

	public EntregaAsistencia buscarPorId(int id);
		
	public void eliminarEntrega(int id);
}
