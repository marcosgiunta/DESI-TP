package tuti.desi.presentacion.familia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.entidades.Familia;
import tuti.desi.servicios.FamiliaService;

@Controller
@RequestMapping("/familia/buscar")
public class FamiliaBuscarController {
	
	@Autowired
	private FamiliaService servicio;
	
	@GetMapping
	public String mostrarFamilia(@RequestParam(required = false) String nombre,
	                             @RequestParam(required = false) Integer nroFamilia,
	                             Model modelo) {

	    if (nroFamilia != null) {
	        modelo.addAttribute("familias", servicio.buscarPorNroFamilia(nroFamilia));
	    } else if (nombre != null && !nombre.isBlank()) {
	        modelo.addAttribute("familias", servicio.buscarPorNombre(nombre));
	    } else {
	        modelo.addAttribute("familias", servicio.listarFamilia());
	    }

	    return "familia/listar";
	}
	
	@GetMapping("/ver/{id}")
	public String verFamilia(@PathVariable Integer id, Model modelo) {
	    Familia fam = servicio.buscarPorId(id)
	        .orElseThrow(() -> new IllegalArgumentException("Familia no encontrada: " + id));
	    modelo.addAttribute("familia", fam);
	    return "familia/ver";
	}
}


