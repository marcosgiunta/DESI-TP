package tuti.desi.presentacion.familia;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import tuti.desi.entidades.Familia;
import tuti.desi.servicios.FamiliaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/familia")
public class FamiliaRegistrarEditarController {

    @Autowired
    private FamiliaService servicioFamilia;

    @GetMapping("/listar")
    public String listar(Model modelo) {
        modelo.addAttribute("familias", servicioFamilia.listarFamilia());
        return "familia/listar";
    }

    @GetMapping("/familiaNueva")
    public String formularioFamilia(Model modelo) {
        FamiliaForm form = new FamiliaForm();
        form.setFechaRegistro(LocalDate.now()); // LocalDate para compatibilidad con el input type="date"
        form.setDeshabilitado(false);           // inicializar deshabilitado
        modelo.addAttribute("familiaForm", form);
        return "familia/alta";
    }

    @GetMapping("/editar/{nroFamilia}")
    public String editarFamilia(@PathVariable("nroFamilia") Integer nroFamilia, Model modelo) {
        Familia familia = servicioFamilia.buscarPorId(nroFamilia)
            .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + nroFamilia));

        FamiliaForm form = new FamiliaForm();
        form.setNroFamilia(familia.getNroFamilia());
        form.setNombre(familia.getNombre());
        if (familia.getFechaRegistro() != null) {
        	System.out.println("Fecha que trae la entidad: " + familia.getFechaRegistro());
        	// Conversión segura
            form.setFechaRegistro(familia.getFechaRegistro().toLocalDate());
        } else {
            // Por si alguna vez guardaste un null en la base
        	System.out.println("Fecha que trae la entidad: " + familia.getFechaRegistro());
        	form.setFechaRegistro(LocalDate.now());
        }

        modelo.addAttribute("familiaForm", form);
        return "familia/alta";
    }

    @PostMapping("/guardar")
    public String salvarFamilia(
        @ModelAttribute("familiaForm") @Valid FamiliaForm formFamilia,
        BindingResult bindingResult,
        Model modelo) {

        System.out.println("----> Entró al método salvarFamilia");
        System.out.println("Fecha que llega del formulario: " + formFamilia.getFechaRegistro());

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println("ERROR: " + error.toString());
            });
            return "familia/alta"; // Volver al formulario si hay error
        }

        // Si viene null porque no se tildó el checkbox
        if (formFamilia.getDeshabilitado() == null) {
            formFamilia.setDeshabilitado(false);
        }

        Familia familia;
        if (formFamilia.getNroFamilia() != null) {
            // Edición
            familia = servicioFamilia.buscarPorId(formFamilia.getNroFamilia())
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + formFamilia.getNroFamilia()));
        } else {
            // Alta
            familia = new Familia();
        }

        // Asignar campos
        familia.setNombre(formFamilia.getNombre());
        familia.setDeshabilitado(formFamilia.getDeshabilitado());

        // Convertir LocalDate a Date de forma correcta
        familia.setFechaRegistro(
                java.sql.Date.valueOf(formFamilia.getFechaRegistro())
            );

        // Guardar en base
        servicioFamilia.salvarFamilia(familia);

        return "redirect:/familia/listar";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        servicioFamilia.eliminar(id);
        return "redirect:/familia/listar";
    }
}
