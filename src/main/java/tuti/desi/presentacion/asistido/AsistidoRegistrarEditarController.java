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
        
		return "asistido/formulario";
        
	}
	

	@PostMapping("/guardar")
	public String salvarAsistido(@ModelAttribute("asistidoForm") @Valid AsistidoForm formAsistido,
			BindingResult bindingResult,
			Model modelo) {
		if (bindingResult.hasErrors()) {
			return "asistido/formulario";
		}
		
		Asistido existePersona = asistidoServicio.findByDni(formAsistido.getDni());
		if (existePersona != null) {
			modelo.addAttribute("error", "Ya existe un asistido con ese DNI");
            return "asistido/formulario";
		}
		
		Familia familia = familiaServicio.buscarPorId(formAsistido.getFamiliaId()).orElse(null);
        if (familia == null) {
            modelo.addAttribute("error", "La familia no existe");
            return "asistido/formulario";
        }
             
	
	Asistido asistido = new Asistido();
    asistido.setDni(formAsistido.getDni());
    asistido.setNombre(formAsistido.getNombre());
    asistido.setApellido(formAsistido.getApellido());
    asistido.setDomicilio(formAsistido.getDomicilio());
    asistido.setOcupacion(formAsistido.getOcupacion());
    asistido.setFechaNacimiento(formAsistido.getFechaNacimiento());
    asistido.setFechaRegistro(new Date());
    asistido.setFamilia(familia);

    asistidoServicio.SalvarAsistido(asistido);
    //System.out.println(mensaje);

    return "redirect:/familia/buscar";
	}
}
