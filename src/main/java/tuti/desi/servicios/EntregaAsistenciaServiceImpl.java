package tuti.desi.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.EntregaAsistenciaRepositorio;
import tuti.desi.entidades.EntregaAsistencia;


@Service
public class EntregaAsistenciaServiceImpl implements EntregaAsistenciaService{
	
	@Autowired
	private EntregaAsistenciaRepositorio repositorio;

	@Override
	public List<EntregaAsistencia> ListarEntregaAsistencia() {
		
		return repositorio.findAll();

	}

	@Override
	public EntregaAsistencia guardarEntrega(EntregaAsistencia entrega) {
	
		return repositorio.save(entrega);
	}

	@Override
	public EntregaAsistencia buscarPorId(int id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public void eliminarEntrega(int id) {
		repositorio.deleteById(id);
	}

}
