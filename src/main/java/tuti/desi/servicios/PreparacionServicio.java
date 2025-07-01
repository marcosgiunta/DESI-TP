package tuti.desi.servicios;

import tuti.desi.entidades.Preparacion;
import java.util.List;

public interface PreparacionServicio {

    Preparacion guardar(Preparacion preparacion);

    List<Preparacion> listarTodas();

    Preparacion buscarPorId(Integer id);

    void eliminar(Integer id);
}
