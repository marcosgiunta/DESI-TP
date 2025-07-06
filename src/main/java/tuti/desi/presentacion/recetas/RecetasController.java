package tuti.desi.presentacion.recetas;

import java.util.ArrayList;
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
import tuti.desi.entidades.Ingrediente;
import tuti.desi.entidades.ItemReceta;
import tuti.desi.entidades.Producto;
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
	public String registrarReceta(Model modelo) {
		Receta nuevaReceta = new Receta();
		modelo.addAttribute("nuevaReceta", nuevaReceta);
		modelo.addAttribute("ingredientes", ingredientesRepositorio.findAll());
		return "recetasAlta";
	}

	@PostMapping("/recetas/Guardar")
	public String guardarReceta(@ModelAttribute("nuevaReceta") Receta receta, 
	                           @RequestParam(value = "ingredienteIds", required = false) Integer[] ingredienteIds,
	                           @RequestParam(value = "cantidades", required = false) Double[] cantidades,
	                           @RequestParam(value = "calorias", required = false) Integer[] calorias,
	                           Model model) {
		try {
			// Validar que se hayan enviado ingredientes
			if (ingredienteIds == null || ingredienteIds.length == 0) {
				model.addAttribute("errorMessage", "Debe agregar al menos un ingrediente a la receta");
				model.addAttribute("ingredientes", ingredientesRepositorio.findAll());
				return "recetas/recetasAlta";
			}

			// Crear lista de ItemReceta a partir de los arrays
			List<ItemReceta> itemsReceta = new ArrayList<>();
			
			for (int i = 0; i < ingredienteIds.length; i++) {
				// Solo procesar si el ingrediente está seleccionado
				if (ingredienteIds[i] != null && ingredienteIds[i] > 0) {
					ItemReceta item = new ItemReceta();
					
					// Buscar el ingrediente
					final int ingredienteId = ingredienteIds[i];
					Ingrediente ingrediente = ingredientesRepositorio.findById(ingredienteId)
							.orElseThrow(() -> new IllegalArgumentException("Ingrediente no encontrado: " + ingredienteId));
					
					item.setIngrediente(ingrediente);
					item.setReceta(receta);
					
					// Asignar cantidad y calorías si están disponibles
					if (cantidades != null && i < cantidades.length && cantidades[i] != null) {
						item.setCantidad(cantidades[i]);
					}
					if (calorias != null && i < calorias.length && calorias[i] != null) {
						item.setCalorias(calorias[i]);
					}
					
					itemsReceta.add(item);
				}
			}

			// Validar que tenga al menos un ingrediente válido
			if (itemsReceta.isEmpty()) {
				model.addAttribute("errorMessage", "Debe seleccionar al menos un ingrediente válido");
				model.addAttribute("ingredientes", ingredientesRepositorio.findAll());
				return "recetas/recetasAlta";
			}
			
			// Asignar los ingredientes a la receta
			receta.setIngredientes(itemsReceta);
			
			servicio.guardarReceta(receta);
			return "redirect:/recetas/Listar";

		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("ingredientes", ingredientesRepositorio.findAll());
			return "recetas/recetasAlta";
		}
	}

	@GetMapping("/recetas/Listar")
	public String ListarRecetas(Model modelo) {

		modelo.addAttribute("recetas", servicio.listarRecetas());

		return "recetas/recetasListar";

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
			@RequestParam(required = false) Integer caloriasMin,
			@RequestParam(required = false) Integer caloriasMax,
			Model modelo) {
		List<Receta> recetas = servicio.buscarPorFiltros(nombreReceta, caloriasMin, caloriasMax);
		modelo.addAttribute("recetas", recetas);
		return "recetas/recetasListar";
	}

	@GetMapping("/recetas/editar/{id}")
	public String editarReceta(@PathVariable int id, Model modelo) {
		Receta receta = servicio.buscarPorId(id);
		if (receta != null) {
			modelo.addAttribute("nuevaReceta", receta);
			modelo.addAttribute("ingredientes", ingredientesRepositorio.findAll());
			return "recetas/recetasAlta";
		}
		return "redirect:/recetas/Listar";
	}

	@PostMapping("/eliminarIngrediente/{idReceta}/{index}")
	public String eliminarIngrediente(@PathVariable int idReceta, @PathVariable int index) {
		servicio.eliminarIngrediente(idReceta, index);
		return "ok";
	}

	@GetMapping("/recetas/nuevoIngrediente")
	public String nuevoIngrediente(@RequestParam int index, Model modelo) {
		ItemReceta itemVacio = new ItemReceta();
		Producto ingredienteTemp = new Producto();
		itemVacio.setIngrediente(ingredienteTemp);
		
		modelo.addAttribute("item", itemVacio);
		modelo.addAttribute("index", index);
		modelo.addAttribute("ingredientes", ingredientesRepositorio.findAll());
		
		return "fragments/ingrediente-row :: ingrediente-row";
	}

}
