package tuti.desi.presentacion.entregaAsistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	public String EliminarEntregaAsistencia(@PathVariable int id) {

		servicio.eliminarEntrega(id);
		
		return "redirect:/entregaAsistencia/Listar";
	}

	





}
