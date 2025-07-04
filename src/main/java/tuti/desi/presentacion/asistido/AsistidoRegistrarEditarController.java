package tuti.desi.presentacion.asistido;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.entidades.Asistido;
import tuti.desi.entidades.Familia;
import tuti.desi.servicios.AsistidoService;
import tuti.desi.servicios.FamiliaService;

import java.time.LocalDate;
import java.time.ZoneId;

@Controller
@RequestMapping("/asistido")
public class AsistidoRegistrarEditarController {

	@Autowired
	private AsistidoService asistidoServicio;
	
	@Autowired
	private FamiliaService familiaServicio;
	
	@GetMapping("/nuevo/{familiaId}")
	public String nuevoAsistido(@PathVariable Integer familiaId, Model modelo) {
	
		AsistidoForm formAsistido = new AsistidoForm();
        
		formAsistido.setFamiliaId(familiaId);
        
		modelo.addAttribute("asistidoForm", formAsistido);
        
		return "asistido/alta";
        
	}
	

	@PostMapping("/guardar")
	public String salvarAsistido(@ModelAttribute("asistidoForm") @Valid AsistidoForm formAsistido,
	                             BindingResult bindingResult,
	                             Model modelo) {
	    
		//Control por si se carga una fecha futura muestre mensaje por listar.html
    	if (formAsistido.getFechaNacimiento() != null && formAsistido.getFechaNacimiento().isAfter(LocalDate.now())) {
            bindingResult.rejectValue("fechaNacimiento", "error.fechaNacimiento", "La fecha no puede ser posterior a la actual");
        }
		
		if (bindingResult.hasErrors()) {
	        return "asistido/alta";
	    }

	    try {
	    	Asistido existePersona = asistidoServicio.findByDni(formAsistido.getDni());
	    
		    if (existePersona != null) {
		        modelo.addAttribute("error", "Ya existe una persona con ese DNI");
		        return "asistido/alta";
		    }
	
		    Familia familia = familiaServicio.buscarPorId(formAsistido.getFamiliaId()).orElse(null);
		    if (familia == null) {
		        modelo.addAttribute("error", "La familia no existe");
		        return "asistido/alta";
		    }
	
		    Asistido asistido = new Asistido();
		    asistido.setDni(formAsistido.getDni());
		    asistido.setNombre(formAsistido.getNombre());
		    asistido.setApellido(formAsistido.getApellido());
		    asistido.setDomicilio(formAsistido.getDomicilio());
		    asistido.setOcupacion(formAsistido.getOcupacion());
		    asistido.setFechaNacimiento(Date.from(formAsistido.getFechaNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		    asistido.setFechaRegistro(LocalDate.now());
		    asistido.setFamilia(familia);
		    asistido.setDeshabilitado(false);
	
		    asistidoServicio.SalvarAsistido(asistido);
		    
		    System.out.println("Guardando asistido DNI=" + formAsistido.getDni());
		    
		    return "redirect:/asistido/ver/" + familia.getNroFamilia();
		}
	    catch (Exception e) {
	        e.printStackTrace(); // opcional para depurar en consola
	        modelo.addAttribute("error", "Ocurrió un error al guardar el asistido.");
	        return "asistido/alta";
	    }
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarAsistido(@PathVariable Integer id) {
	    Asistido as = asistidoServicio.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Asistido no encontrado: " + id));

	    asistidoServicio.eliminar(id); //  borrado lógico

	    System.out.println("Asistido Eliminado");
	    
	    return "redirect:/asistido/ver/" + as.getFamilia().getNroFamilia(); 
	}

    @GetMapping("/ver/{id}")
    public String verFamilia(@PathVariable Integer id, Model model) {
        Familia fam = familiaServicio.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Familia no encontrada: " + id));
        model.addAttribute("familia", fam);
        return "asistido/ver";
    }
    
}

