package tuti.desi.presentacion.preparacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.accesoDatos.RecetasRepositorio;
import tuti.desi.entidades.Preparacion;
import tuti.desi.entidades.Receta;
import tuti.desi.servicios.PreparacionServicio;

import java.util.Date;
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
        List<Receta> recetas = recetasRepositorio.findByEliminadaFalse();
        model.addAttribute("recetas", recetas);
        model.addAttribute("preparacion", new Preparacion());
        return "preparacion/altaPreparacion";
    }

    // GUARDAR PREPARACIÓN
    @PostMapping("/preparacion/guardar")
    public String guardarPreparacion(@ModelAttribute("preparacion") Preparacion preparacion, Model model) {
        preparacion.setEliminado(false);
        if (preparacion.getTotalRacionesPreparadas() != null) {
            preparacion.setStockRacionesRestantes(preparacion.getTotalRacionesPreparadas());
        } else {
            preparacion.setStockRacionesRestantes(0);
        }
        try {
            preparacionServicio.guardar(preparacion); 
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("preparacion", preparacion);
            model.addAttribute("recetas", recetasRepositorio.findAll());
            return "preparacion/altaPreparacion";
        }
        return "redirect:/preparacion/Listado";
    }

    // LISTAR PREPARACIONES
    @GetMapping("/preparacion/Listado")
    public String listarPreparaciones(Model model) {
        List<Preparacion> preparaciones = preparacionServicio.listarTodas();
        model.addAttribute("preparaciones", preparaciones);
        return "preparacion/listadoPreparaciones";
    }



    // MOSTRAR FORMULARIO DE MODIFICACIÓN
    @GetMapping("/preparacion/modificar/{id}")
    public String mostrarFormularioModificacion(@PathVariable("id") Integer id, Model model) {
        Preparacion preparacion = preparacionServicio.buscarPorId(id);
        List<Receta> recetas = recetasRepositorio.findByEliminadaFalse(); 
        model.addAttribute("preparacion", preparacion);
        model.addAttribute("recetas", recetas);
        return "preparacion/modificarPreparacion";
    }

    @PostMapping("/preparacion/actualizar")
    public String actualizarPreparacion(@ModelAttribute("preparacion") Preparacion nuevaPreparacion, Model model) {
        if (nuevaPreparacion.getFechaPreparacion().after(new Date())) {
            model.addAttribute("error", "La fecha no puede ser futura.");
            model.addAttribute("preparacion", nuevaPreparacion);
            return "preparacion/modificarPreparacion";
        }

        Preparacion existente = preparacionRepositorio.findById(nuevaPreparacion.getId()).orElse(null);
        if (existente != null) {
            existente.setFechaPreparacion(nuevaPreparacion.getFechaPreparacion());
            preparacionRepositorio.save(existente);
        }

        return "redirect:/preparacion/Listado";
    }

    @PostMapping("/preparacion/eliminar/{id}")
    public String eliminarPreparacion(@PathVariable("id") Integer id) {
        Preparacion preparacion = preparacionRepositorio.findById(id).orElse(null);
        if (preparacion != null) {
            preparacion.setEliminado(true);
            preparacionRepositorio.save(preparacion);
        }

        return "redirect:/preparacion/Listado";
    }

    // FILTRAR PREPARACIONES
    @GetMapping("/preparacion/Filtrar")
    public String FiltrarPreparacion(
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
        @RequestParam(required = false) String nombreReceta,
        Model modelo) {

        List<Preparacion> preparaciones = preparacionServicio.buscarPorFechaYReceta(fecha, nombreReceta);
        for (Preparacion p : preparaciones) {
            Integer totalCalorias = preparacionServicio.obtenerCaloriasTotalesPorReceta(p.getReceta().getId());
            if (totalCalorias != null) {
                p.setCaloriasPorRacion(totalCalorias);
            }
        }
        modelo.addAttribute("preparaciones", preparaciones);
        return "preparacion/listadoPreparaciones";
    }
    
}
