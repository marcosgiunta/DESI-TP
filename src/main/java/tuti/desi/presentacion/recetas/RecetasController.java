package tuti.desi.presentacion.recetas;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.entidades.Receta;

import tuti.desi.servicios.RecetasService;


@Controller
public class RecetasController {
	
	@Autowired
	private RecetasService servicio;

    // @Autowired
    // private IngredienteService ingredienteService;

	@GetMapping("/recetas/Alta")
	public String RegistrarReceta(Model modelo) {
		Receta nuevaReceta = new Receta();
		modelo.addAttribute("nuevaReceta", nuevaReceta);
		
        // modelo.addAttribute("ingredientes", ingredienteService.listarIngredientes());
		return "recetaAlta";
	}

    // public List<Ingrediente> listarIngredientes() {
    //     return ingredienteRepository.findAll();
    // }
	
	
	@PostMapping("/recetas/Guardar")
	public String GuardarRecetas(@ModelAttribute("nuevaReceta") Receta receta) {
		servicio.guardarReceta(receta);
		
		return "redirect:/recetas/Listar";
	}

    @GetMapping("/recetas/Listar")
	public String ListarRecetas(Model modelo) {

		modelo.addAttribute("recetas", servicio.listarRecetas());

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
		List<Receta> recetas = servicio.buscarPorFiltros(nombreReceta);
		modelo.addAttribute("recetas", recetas);
		return "recetasListar";
	}
}
