package tuti.desi.presentacion.familia;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import tuti.desi.accesoDatos.VistaFechasEntregasRespositorio;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.vistaUltimaEntega;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import tuti.desi.presentacion.asistido.AsistidoForm;

@Controller
@RequestMapping("/familia")
public class FamiliaRegistrarEditarController {

    @Autowired
    private FamiliaService servicioFamilia;
    
    @Autowired
    private AsistidoService servicioAsistido;
    
    @Autowired
	private VistaFechasEntregasRespositorio ultimaEntegraRepo;

    @GetMapping("/listar")
    public String listar(@RequestParam(required = false) String nombre,
                         @RequestParam(required = false) Integer nroFamilia,
                         Model modelo) {

        List<Familia> familias;

        if (nroFamilia != null) {
            familias = servicioFamilia.buscarPorNroFamilia(nroFamilia);
        } else if (nombre != null && !nombre.isBlank()) {
            familias = servicioFamilia.buscarPorNombre(nombre);
        } else {
            familias = servicioFamilia.listarFamilia();
        }

        modelo.addAttribute("familias", familias);

        List<vistaUltimaEntega> entregas = ultimaEntegraRepo.findAll();
        Map<String, LocalDate> ultimaFechaPorFamilia = new HashMap<>();

        for (vistaUltimaEntega v : entregas) {
            ultimaFechaPorFamilia.put(String.valueOf(v.getNroFamilia()), v.getUltimaFecha());
        }

    
        modelo.addAttribute("ultimaFechaPorFamilia", ultimaFechaPorFamilia);

        return "familia/listar";
    }
    
    @GetMapping("/alta")
    public String altaFamilia(Model modelo) {
        FamiliaForm form = new FamiliaForm();
        form.setFechaRegistro(LocalDate.now()); 
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
            return "familia/alta"; 
        }

        // Creamos un objeto Familia
        Familia familia;

        // Si la familia tiene un nroFamilia (es una edición)
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

        //Control de DNI en tiempo de carga
        Set<Integer> dnisUnicos = new HashSet<>();
        List<AsistidoForm> integrantes = formFamilia.getIntegrantes();

        for (int i = 0; i < integrantes.size(); i++) {
            Integer dni = integrantes.get(i).getDni();

            // Ignorar DNIs nulos
            if (dni != null && !dnisUnicos.add(dni)) {
                bindingResult.rejectValue("integrantes[" + i + "].dni", null, "DNI duplicado en carga");
            }
        }

        if (bindingResult.hasErrors()) {
            modelo.addAttribute("modoEdicion", formFamilia.getNroFamilia() != null);
            return "familia/alta";
        }
        
               
        // Control de DNI contra la base de datos
        for (AsistidoForm af : formFamilia.getIntegrantes()) {
            Asistido asistidoExistente = servicioAsistido.findByDni(af.getDni());
            if (asistidoExistente != null) {
                // Si existe, mostramos un error y no lo agregamos
                throw new IllegalArgumentException("Ya existe una persona con ese DNI " + af.getDni());
            }

            // Si no existe, agregamos el nuevo asistido
            Asistido a = new Asistido();
            a.setDni(af.getDni());
            a.setApellido(af.getApellido());
            a.setNombre(af.getNombre());
            LocalDate ld = af.getFechaNacimiento();
            Date fechaNacimiento = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
            a.setFechaNacimiento(fechaNacimiento);
            a.setDomicilio(af.getDomicilio());
            a.setOcupacion(af.getOcupacion());
            a.setFechaRegistro(LocalDate.now());
            a.setFamilia(familia);
            familia.getIntegrantesFamiliaAsistida().add(a);
        }

        // Si la familia es nueva, el campo nroFamilia es null y se asigna automáticamente al guardar
        servicioFamilia.salvarFamilia(familia); // Guardamos la familia (esto persistirá la familia y sus integrantes)

       return "redirect:/familia/listar"; // Redirigir al listado de familias
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        servicioFamilia.eliminar(id);
        return "redirect:/familia/listar";
    }
    
    @GetMapping("/ver/{id}")
    public String verFamilia(@PathVariable Integer id, Model modelo) {
        Familia fam = servicioFamilia.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Familia no encontrada: " + id));
        modelo.addAttribute("familia", fam);
        return "familia/ver";
    }
}
