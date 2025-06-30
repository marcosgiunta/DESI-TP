package tuti.desi.presentacion.familia;

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
        return "familia/familiaListar";
    }
	
	@GetMapping("/familiaNueva")
    public String formularioFamilia(Model modelo) {
    
		modelo.addAttribute("familiaForm", new FamiliaForm());
        
		return "familia/nueva"; 
    
	}
	
	@PostMapping("guardar")
	public String salvarFamilia(@ModelAttribute("familiaForm") @Valid FamiliaForm formFamilia,
			BindingResult bindingResult,
			Model modelo) {
		
		if (bindingResult.hasErrors()) {
			return "familia/formulario";
			
		}
		
		Familia familia = new Familia();
		familia.setNombre(formFamilia.getNombre());
		familia.setFechaRegistro(new Date());
		familia.setDeshabilitado(false);
		
		servicioFamilia.salvarFamilia(familia);
		
		return "redirect:/familia/buscar";
		
	}
	
	@GetMapping("/deshabilitar/{id}")
	public String eliminar(@PathVariable("id") Integer id) {
		servicioFamilia.eliminar(id);
		return "redirect:/familia/buscar";
	}
}
