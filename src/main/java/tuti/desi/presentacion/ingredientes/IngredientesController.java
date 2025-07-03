package tuti.desi.presentacion.ingredientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tuti.desi.entidades.Ingrediente;
import tuti.desi.servicios.IngredientesService;

@Controller
@RequestMapping("/ingredientes")
public class IngredientesController {

    @Autowired
    private IngredientesService servicio;

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
    public String eliminarIngrediente(@PathVariable Integer id) {
        servicio.eliminar(id);
        return "redirect:/recetas/Listar";
    }
}
