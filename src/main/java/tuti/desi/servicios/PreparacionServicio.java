package tuti.desi.servicios;

import tuti.desi.entidades.Preparacion;

import java.util.Date;
import java.util.List;

public interface PreparacionServicio {

    Preparacion guardar(Preparacion preparacion);

    List<Preparacion> listarTodas();

    Preparacion buscarPorId(Integer id);
    
    Integer obtenerCaloriasTotalesPorReceta(Integer recetaId);

    void eliminar(Integer id);

    List<Preparacion> buscarPorFechaYReceta(Date fecha, String nombreReceta);
}