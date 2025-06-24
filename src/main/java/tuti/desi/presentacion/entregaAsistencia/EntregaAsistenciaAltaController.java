package tuti.desi.presentacion.entregaAsistencia;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tuti.desi.entidades.EntregaAsistencia;

import tuti.desi.servicios.EntregaAsistenciaService;


@Controller
public class EntregaAsistenciaAltaController {
	
	

	@Autowired
	private EntregaAsistenciaService servicio;

	@GetMapping("/entregaAsistencia/Alta")
	public String RegistrarEntregaAsistencia(Model modelo) {
		EntregaAsistencia entregaNueva = new EntregaAsistencia();

		//List<Familia> familias =familiaRepo.findAll();
		//List<Preparacion> platos = servicio.listarPreparaciones();

		modelo.addAttribute("entregaNueva", entregaNueva);
		
		return "entregaAsistenciaAlta";
		
	}
	
	
	@PostMapping("/entregaAsistencia/Guardar")
	public String GuardarEntregaAsistencia(@ModelAttribute("entregaNueva") EntregaAsistencia entrega) {

		LocalDate hoy = LocalDate.now();


		entrega.setFecha(hoy);
		servicio.guardarEntrega(entrega);
		
		return "redirect:/entregaAsistencia/Listar";
		
	}
	

	
}
