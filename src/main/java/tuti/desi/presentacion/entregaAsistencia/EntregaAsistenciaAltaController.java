package tuti.desi.presentacion.entregaAsistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tuti.desi.accesoDatos.FamiliaRepositorio;
import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.entidades.EntregaAsistencia;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.Preparacion;
import tuti.desi.servicios.EntregaAsistenciaService;

@Controller
public class EntregaAsistenciaAltaController {

	@Autowired
	private FamiliaRepositorio familiaRepo;

	@Autowired
	private PreparacionRepositorio preparacionRepo;

	@Autowired
	private EntregaAsistenciaService servicio;

	@GetMapping("/entregaAsistencia/Alta")
	public String RegistrarEntregaAsistencia(Model modelo) {
		EntregaAsistencia entregaNueva = new EntregaAsistencia();

		modelo.addAttribute("entregaNueva", entregaNueva);

		modelo.addAttribute("nrosFamilia", familiaRepo.findAllNroFamilia());
		modelo.addAttribute("preparaciones", preparacionRepo.findByEliminadoFalse());

		return "entregaAsistenciaAlta";

	}

	@PostMapping("/entregaAsistencia/Guardar")
	public String GuardarEntregaAsistencia(@ModelAttribute("entregaNueva") EntregaAsistencia entrega, Model modelo) {

		try {
			// Busca y setea la familia
			if (entrega.getFamilia() != null) {
				Integer nroFamilia = entrega.getFamilia().getNroFamilia();
				Familia familia = familiaRepo.findByNroFamilia(nroFamilia);
				entrega.setFamilia(familia);
			}
            // Busca y setea la preparacion
			if (entrega.getPreparacion() != null) {
				Preparacion preparacion = preparacionRepo.findById(entrega.getPreparacion().getId()).orElse(null);
				entrega.setPreparacion(preparacion);
			}
			servicio.guardarEntrega(entrega);

			return "redirect:/entregaAsistencia/Listar";

		} catch (IllegalArgumentException ex) {
			
			// Vuelve a carga los datos necesarios para el formulario para no tener que volver a cargarlos
			modelo.addAttribute("entregaNueva", entrega);
			modelo.addAttribute("nrosFamilia", familiaRepo.findAllNroFamilia());
			modelo.addAttribute("preparaciones", preparacionRepo.findByEliminadoFalse());
			modelo.addAttribute("errorMessage", ex.getMessage());
			return "entregaAsistenciaAlta";
		}

	}
}
