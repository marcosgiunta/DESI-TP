package tuti.desi.presentacion.ingredientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.servicios.IngredientesService;
import tuti.desi.servicios.RecetasService;
import tuti.desi.entidades.Receta;

@Controller
@RequestMapping("/ingredientes")
public class IngredientesController {

    @Autowired
    private IngredientesService servicio;
    
    @Autowired
    private RecetasService servicioRecetas;

    @GetMapping("/Alta")
    public String mostrarFormularioAlta(Model modelo) {
        modelo.addAttribute("ingrediente", new Ingrediente());
        return "ingredientesAlta";
    }

    @PostMapping("/Guardar")
    public String guardarIngrediente(@ModelAttribute Ingrediente ingrediente) {
        servicio.guardar(ingrediente);
        return "redirect:/recetas/Listar";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminarIngrediente(
            @PathVariable int idReceta,
            @PathVariable int index,
            Model model
    ) {
        Receta receta = servicioRecetas.buscarPorId(idReceta);
        if (receta != null && receta.getIngredientes().size() > index) {
            receta.getIngredientes().remove(index);
            servicioRecetas.guardarReceta(receta); 
        }
        model.addAttribute("nuevaReceta", receta);
        return "redirect:/recetas/editar/" + idReceta;
    }
}
