package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.entidades.Preparacion;

import java.util.List;
import java.util.Optional;

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
    public Preparacion buscarPorId(Long id) {
        Optional<Preparacion> resultado = preparacionRepositorio.findById(id);
        return resultado.orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        preparacionRepositorio.deleteById(id);
    }
}
