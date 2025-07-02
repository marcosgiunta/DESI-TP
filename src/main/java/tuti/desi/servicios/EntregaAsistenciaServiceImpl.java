package tuti.desi.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.EntregaAsistenciaRepositorio;
import tuti.desi.entidades.EntregaAsistencia;

@Service
public class EntregaAsistenciaServiceImpl implements EntregaAsistenciaService {

	@Autowired
	private EntregaAsistenciaRepositorio repositorio;

	@Override
	public List<EntregaAsistencia> ListarEntregaAsistencia() {

		return repositorio.findAll();

	}

	@Override
	public EntregaAsistencia guardarEntrega(EntregaAsistencia entrega) {

		LocalDate hoy = LocalDate.now();

        // Verifica si la preparación tiene suficientes raciones restantes
		if (entrega.getPreparacion().getStockRacionesRestantes() >= entrega.getCantidadRaciones()) {

			entrega.getPreparacion().setStockRacionesRestantes(
					entrega.getPreparacion().getStockRacionesRestantes() - entrega.getCantidadRaciones());

		} else {
			throw new IllegalArgumentException("No hay suficientes raciones disponibles para esta entrega.");
		}


		// Verifica si ya existe una entrega para la misma familia en la fecha de hoy
		//si existe, lanza una excepción
		EntregaAsistencia existente = repositorio.findByFamiliaAndFecha(entrega.getFamilia(), hoy);
		if (existente != null) {

			throw new IllegalArgumentException("Ya se realizó una entrega para esta familia en la fecha de hoy.");

		}


        //busca la cantidad de integrantes de la familia
		//y verifica que la cantidad de raciones no sea mayor que la cantidad de integrantes
		Integer cantidadIntegrantes = entrega.getFamilia().getIntegrantesFamiliaAsistida().size();

		if (entrega.getCantidadRaciones() > cantidadIntegrantes) {

			throw new IllegalArgumentException(
					"La cantidad de raciones no puede ser mayor que la cantidad de integrantes de la familia.");
		}

		entrega.setFecha(hoy);

		return repositorio.save(entrega);
	}


	@Override
	public EntregaAsistencia buscarPorId(Integer id) {

		// Busca la entrega por ID y devuelve el objeto EntregaAsistencia
		// Si no se encuentra, devuelve null

		return repositorio.findById(id).orElse(null);
	}




	@Override
	public void eliminarEntrega(Integer id) {


		// Verifica si la entrega existe
		// Si no existe, lanza una excepción
		EntregaAsistencia entrega = repositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Entrega no encontrada"));
				
        // Aumenta el stock de raciones restantes en la preparación asociada a la entrega eliminada
		entrega.getPreparacion().setStockRacionesRestantes(
				entrega.getPreparacion().getStockRacionesRestantes() + entrega.getCantidadRaciones());

		repositorio.deleteById(id);
	}

	@Override
	public List<EntregaAsistencia> buscarPorFiltros(LocalDate fecha, Integer nroFamilia, String nombreFamilia) {

		return repositorio.buscarPorFiltros(fecha, nroFamilia, nombreFamilia);
	}

}
