package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.entidades.Preparacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PreparacionServicioImpl implements PreparacionServicio {

    @Autowired
    private PreparacionRepositorio preparacionRepositorio;

    @Override
    public Preparacion guardar(Preparacion preparacion) {

 if (preparacion.getFechaPreparacion().after(new Date())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }

List<Preparacion> existentes = preparacionRepositorio.buscarPorFiltros(
            preparacion.getFechaPreparacion(), 
            preparacion.getReceta().getNombre()
        );
        for (Preparacion p : existentes) {
            if (p.getReceta().getId().equals(preparacion.getReceta().getId())) {
                throw new IllegalArgumentException("Ya existe una preparación de esta receta para la fecha indicada.");
            }
        }


 //if (!hayStockSuficiente(preparacion)) {
           // throw new IllegalArgumentException("No hay stock suficiente para preparar la cantidad de raciones indicadas.");
      //  }


            //private boolean hayStockSuficiente(Preparacion preparacion) {
        // Aquí deberías recorrer los ingredientes de la receta y chequear el stock
        // Retornar true si hay stock suficiente, false si no
       // return true; // Implementa la lógica real aquí
    //}


        return preparacionRepositorio.save(preparacion);




        
    }

    @Override
    public List<Preparacion> listarTodas() {
        return preparacionRepositorio.findAll();
    }

    @Override
    public Preparacion buscarPorId(Integer id) {
        Optional<Preparacion> resultado = preparacionRepositorio.findById(id);
        return resultado.orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        preparacionRepositorio.deleteById(id);
    }

    @Override
    public List<Preparacion> buscarPorFechaYReceta(Date fecha, String nombreReceta) {
        return preparacionRepositorio.buscarPorFiltros(fecha, nombreReceta);
    }
}
