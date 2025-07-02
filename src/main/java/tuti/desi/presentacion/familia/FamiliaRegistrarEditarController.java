package tuti.desi.presentacion.familia;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import tuti.desi.presentacion.asistido.AsistidoForm;

@Controller
@RequestMapping("/familia")
public class FamiliaRegistrarEditarController {

    @Autowired
    private FamiliaService servicioFamilia;
    
    @Autowired
    private AsistidoService servicioAsistido;

    @GetMapping("/listar")
    public String listar(Model modelo) {
        modelo.addAttribute("familias", servicioFamilia.listarFamilia());
        return "familia/listar";
    }

    @GetMapping("/familiaNueva")
    public String altaFamilia(Model modelo) {
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
            form.setFechaRegistro(familia.getFechaRegistro());
        } else {
            form.setFechaRegistro(LocalDate.now());
        }
        
        modelo.addAttribute("familiaForm", form);
        return "familia/alta";
    }

    @PostMapping("/guardar")
    public String salvarFamilia(@ModelAttribute("familiaForm") @Valid FamiliaForm formFamilia,
                                 BindingResult bindingResult,
                                 Model modelo) {

        // Si hay errores en el formulario, volvemos a mostrarlo
        if (bindingResult.hasErrors()) {
            return "familia/alta"; // Si hay errores, volvemos al formulario
        }

        // Creamos un objeto Familia
        Familia familia;

        // Si la familia tiene un nroFamilia (es una edición), la buscamos
        if (formFamilia.getNroFamilia() != null) {
            familia = servicioFamilia.buscarPorId(formFamilia.getNroFamilia())
                    .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + formFamilia.getNroFamilia()));
        } else {
            // Si no tiene nroFamilia, es una nueva familia (sin ID aún)
            familia = new Familia(); 
        }

        // Asignamos los valores a la familia
        familia.setNombre(formFamilia.getNombre());
        familia.setDeshabilitado(formFamilia.getDeshabilitado());
        familia.setFechaRegistro(formFamilia.getFechaRegistro());

        // Aquí es donde los integrantes deben ser gestionados
        for (AsistidoForm af : formFamilia.getIntegrantes()) {
            Asistido asistidoExistente = servicioAsistido.findByDni(af.getDni());
            if (asistidoExistente != null) {
                // Si existe, mostramos un error y no lo agregamos
                throw new IllegalArgumentException("Ya existe un integrante con el DNI " + af.getDni());
            }

            // Si no existe, agregamos el nuevo asistido
            Asistido a = new Asistido();
            a.setDni(af.getDni());
            a.setApellido(af.getApellido());
            a.setNombre(af.getNombre());
            LocalDate ld = af.getFechaNacimiento();
            Date fechaNacimiento = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
            a.setFechaNacimiento(fechaNacimiento);
            a.setOcupacion(af.getOcupacion());
            a.setFechaRegistro(new Date());
            a.setFamilia(familia);
            familia.getIntegrantesFamiliaAsistida().add(a);
        }

        // Si la familia es nueva, el campo nroFamilia es null y se asigna automáticamente al guardar
        servicioFamilia.salvarFamilia(familia); // Guardamos la familia (esto persistirá la familia y sus integrantes)
        System.out.println("Familia guardada con ID: " + familia.getNroFamilia());
        
        return "redirect:/familia/listar"; // Redirigir al listado de familias
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        servicioFamilia.eliminar(id);
        return "redirect:/familia/listar";
    }
    
    
}
