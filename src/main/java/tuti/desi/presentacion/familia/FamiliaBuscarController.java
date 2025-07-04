package tuti.desi.presentacion.familia;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import tuti.desi.accesoDatos.VistaFechasEntregasRespositorio;
import tuti.desi.entidades.Familia;
import tuti.desi.entidades.vistaUltimaEntega;
import tuti.desi.servicios.FamiliaService;


@Controller
@RequestMapping("/familia")
public class FamiliaBuscarController {
	
	//@Autowired
	//private FamiliaService servicio;
	
	//@Autowired
	//private VistaFechasEntregasRespositorio ultimaEntegraRepo;
	
	//@GetMapping
	//public String mostrarFamilia(@RequestParam(required = false) String nombre,
			//@RequestParam(required = false) Integer nroFamilia,
	                             //                       Model modelo) {

		// List<Familia> familias;

	    //if (nroFamilia != null) {
	    	//familias = servicio.buscarPorNroFamilia(nroFamilia);
	        // } else if (nombre != null && !nombre.isBlank()) {
	    	//familias = servicio.buscarPorNombre(nombre);
	        //  } else {
	    	// familias = servicio.listarFamilia();
	        //}

	    //modelo.addAttribute("familias", familias);

	    // Obtenemos los datos de la vista y los convertimos en un Map
	    //List<vistaUltimaEntega> entregas = ultimaEntegraRepo.findAll();
	    //Map<Integer, LocalDate> ultimaFechaPorFamilia = new HashMap<>();

	    //for (vistaUltimaEntega v : entregas) {
	    	//      ultimaFechaPorFamilia.put(v.getNroFamilia(), v.getUltimaFecha());
	  //  }

	    // Verificación: impresión del contenido real del mapa
	 //   System.out.println("MAPA DE ULTIMAS FECHAS:");
	//    ultimaFechaPorFamilia.forEach((nro, fecha) -> {
	////        System.out.println("Familia " + nro + " -> " + fecha);
	 //   });

	 //   modelo.addAttribute("ultimaFechaPorFamilia", ultimaFechaPorFamilia != null ? ultimaFechaPorFamilia : new HashMap<>());


	//    return "familia/listar";
	//}
	
	//@GetMapping("/ver/{id}")
	//public String verFamilia(@PathVariable Integer id, Model modelo) {
	//    Familia fam = servicio.buscarPorId(id)
	//        .orElseThrow(() -> new IllegalArgumentException("Familia no encontrada: " + id));
	//    modelo.addAttribute("familia", fam);
	//    return "familia/ver";
	//}
}


