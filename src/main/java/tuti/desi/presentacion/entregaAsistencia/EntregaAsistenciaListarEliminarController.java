package tuti.desi.presentacion.entregaAsistencia;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.entidades.EntregaAsistencia;
import tuti.desi.servicios.EntregaAsistenciaService;

@Controller
public class EntregaAsistenciaListarEliminarController {

	@Autowired
	private EntregaAsistenciaService servicio;

	@GetMapping("/entregaAsistencia/Listar")
	public String ListarEntregaAsistencia(Model modelo) {

		modelo.addAttribute("entregas", servicio.ListarEntregaAsistencia());

		return "entregaAsistenciaListar";

	}

	@GetMapping("/entregaAsistencia/Eliminar/{id}")
	public String EliminarEntregaAsistencia(@PathVariable Integer id) {

		servicio.eliminarEntrega(id);

		return "redirect:/entregaAsistencia/Listar";
	}
    
	//filtra por fecha, nroFamilia y nombreFamilia
	//se pueden cargar todos los filtros o solo algunos
	@GetMapping("/entregaAsistencia/Filtrar")
	public String FiltrarEntregaAsistencia(
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
			@RequestParam(required = false) Integer nroFamilia,
			@RequestParam(required = false) String nombreFamilia,
			Model modelo) {
		List<EntregaAsistencia> entregas = servicio.buscarPorFiltros(fecha, nroFamilia, nombreFamilia);
		modelo.addAttribute("entregas", entregas);
		return "entregaAsistenciaListar";
	}

}
