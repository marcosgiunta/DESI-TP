package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.entidades.Preparacion;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class PreparacionServicioImpl implements PreparacionServicio {

    @Autowired
    private PreparacionRepositorio preparacionRepositorio;

    @Override
    public Preparacion guardar(Preparacion preparacion) {
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
    public List<Preparacion> filtrarPreparaciones(LocalDate fecha, String nombreReceta) {
        List<Preparacion> todas = preparacionRepositorio.findByEliminadoFalse();

        return todas.stream()
            .filter(p -> {
                if (fecha == null) return true;
                
                LocalDate fechaPrep = ((java.sql.Date) p.getFechaPreparacion()).toLocalDate();
                return fechaPrep.equals(fecha);
            })
            .filter(p -> (nombreReceta == null || nombreReceta.isEmpty() ||
                          p.getReceta().getNombre().toLowerCase().contains(nombreReceta.toLowerCase())))
            .toList();
    }
}
