package tuti.desi.presentacion.entregaAsistencia;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tuti.desi.entidades.Recetas;

import tuti.desi.servicios.RecetasService;


@Controller
public class RecetasController {
	
	@Autowired
	private RecetasService servicio;

    // @Autowired
    // private IngredienteService ingredienteService;

	@GetMapping("/recetas/Alta")
	public String RegistrarReceta(Model modelo) {
		Recetas nuevaReceta = new Recetas();
		modelo.addAttribute("nuevaReceta", nuevaReceta);
		
        // modelo.addAttribute("ingredientes", ingredienteService.listarIngredientes());
		return "recetaAlta";
	}

    // public List<Ingrediente> listarIngredientes() {
    //     return ingredienteRepository.findAll();
    // }
	
	
	@PostMapping("/recetas/Guardar")
	public String GuardarRecetas(@ModelAttribute("nuevaReceta") Recetas receta) {
		LocalDate hoy = LocalDate.now();
		receta.setFecha(hoy);
		servicio.guardarReceta(receta);
		
		return "redirect:/recetas/Listar";
	}

    @GetMapping("/recetas/Listar")
	public String ListarRecetas(Model modelo) {

		modelo.addAttribute("recetas", servicio.ListarRecetas());

		return "recetasListar";

	}

	@GetMapping("/recetas/Eliminar/{id}")
	public String EliminarReceta(@PathVariable int id) {

		servicio.eliminarReceta(id);

		return "redirect:/recetas/Listar";
	}

	@GetMapping("/recetas/Filtrar")
	public String FiltrarRecetas(
			@RequestParam(required = false) String nombreReceta,
			Model modelo) {
		List<Recetas> recetas = servicio.buscarPorFiltros(nombreReceta);
		modelo.addAttribute("recetas", recetas);
		return "recetasListar";
	}
}
