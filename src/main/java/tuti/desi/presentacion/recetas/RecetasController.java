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

import tuti.desi.accesoDatos.IngredientesRepositorio;
import tuti.desi.accesoDatos.RecetasRepositorio;
import tuti.desi.entidades.ItemReceta;
import tuti.desi.entidades.Receta;

import tuti.desi.servicios.RecetasService;


@Controller
public class RecetasController {
	
	@Autowired
	private RecetasService servicio;
	
	@Autowired
	private RecetasRepositorio recetasRepositorio;

    @Autowired
    private IngredientesRepositorio ingredientesRepositorio;

	@GetMapping("/recetas/Alta")
	public String RegistrarReceta(Model modelo) {
		Receta nuevaReceta = new Receta();
		modelo.addAttribute("nuevaReceta", nuevaReceta);
		modelo.addAttribute("ingredientes", ingredientesRepositorio.findAll());
		return "recetasAlta";
	}

	@PostMapping("/recetas/Guardar")
	public String guardarReceta(@ModelAttribute("nuevaReceta") Receta receta) {
	    for (ItemReceta item : receta.getIngredientes()) {
	        item.setReceta(receta);
	    }
	    servicio.guardarReceta(receta);
	    return "redirect:/recetas/Listar";
	}
	

    @GetMapping("/recetas/Listar")
	public String ListarRecetas(Model modelo) {

		modelo.addAttribute("recetas", servicio.listarRecetas());

		return "recetasListar";

	}

    @PostMapping("/recetas/eliminar/{id}")
    public String eliminarReceta(@PathVariable("id") Integer id) {
        Receta receta = recetasRepositorio.findById(id).orElse(null);
        if (receta != null) {
        	receta.setEliminada(true);
            recetasRepositorio.save(receta);
        }
        return "redirect:/recetas/Listar";
    }

	@GetMapping("/recetas/Filtrar")
	public String FiltrarRecetas(
			@RequestParam(required = false) String nombreReceta,
			@RequestParam(required = false) Integer caloriasReceta,
			Model modelo) {
		List<Receta> recetas = servicio.buscarPorFiltros(nombreReceta, caloriasReceta);
		modelo.addAttribute("recetas", recetas);
		return "recetasListar";
	}
	
	@GetMapping("/recetas/editar/{id}")
	public String editarReceta(@PathVariable int id, Model modelo) {
	    Receta receta = servicio.buscarPorId(id);
	    if (receta != null) {
	        modelo.addAttribute("nuevaReceta", receta);
	        return "recetasAlta";
	    }
	    return "redirect:/recetas/Alta";
	}
}
