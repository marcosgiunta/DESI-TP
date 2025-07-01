package tuti.desi.presentacion.preparacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.accesoDatos.RecetasRepositorio;
import tuti.desi.entidades.Preparacion;
import tuti.desi.entidades.Receta;
import tuti.desi.servicios.PreparacionServicio;

import java.util.List;

@Controller
public class PreparacionController {

    @Autowired
    private PreparacionRepositorio preparacionRepositorio;

    @Autowired
    private RecetasRepositorio recetasRepositorio;

    @Autowired
    private PreparacionServicio preparacionServicio;

    // MOSTRAR FORMULARIO DE ALTA
    @GetMapping("/preparacion/alta")
    public String mostrarFormularioAlta(Model model) {
        List<Receta> recetas = recetasRepositorio.findAll();
        model.addAttribute("recetas", recetas);
        model.addAttribute("preparacion", new Preparacion());
        return "altaPreparacion";
    }

    // GUARDAR PREPARACIÓN
    @PostMapping("/preparacion/guardar")
    public String guardarPreparacion(@ModelAttribute("preparacion") Preparacion preparacion) {
        preparacionRepositorio.save(preparacion);
        return "redirect:/preparacion/listado";
    }

    // LISTAR PREPARACIONES
    @GetMapping("/preparacion/listado")
    public String listarPreparaciones(Model model) {
        List<Preparacion> preparaciones = preparacionRepositorio.findAll();
        model.addAttribute("preparaciones", preparaciones);
        return "listadoPreparaciones";
    }

    // MOSTRAR FORMULARIO DE MODIFICACIÓN
    @GetMapping("/preparacion/modificar/{id}")
    public String mostrarFormularioModificacion(@PathVariable("id") Long id, Model model) {
        Preparacion preparacion = preparacionServicio.buscarPorId(id);
        List<Receta> recetas = recetasRepositorio.findAll();
        model.addAttribute("preparacion", preparacion);
        model.addAttribute("recetas", recetas);
        return "modificarPreparacion";
    }

    // ACTUALIZAR PREPARACIÓN
    @PostMapping("/preparacion/actualizar")
    public String actualizarPreparacion(@ModelAttribute("preparacion") Preparacion preparacion) {
        preparacionRepositorio.save(preparacion);
        return "redirect:/preparacion/listado";
    }

    @PostMapping("/preparacion/eliminar/{id}")
    public String eliminarPreparacion(@PathVariable("id") Long id) {
        preparacionRepositorio.deleteById(id);
        return "redirect:/preparacion/listado";
    }

}
