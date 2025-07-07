package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.ItemRecetaRepositorio;
import tuti.desi.accesoDatos.PreparacionRepositorio;
import tuti.desi.accesoDatos.ProductoRepositorio;
import tuti.desi.entidades.ItemReceta;
import tuti.desi.entidades.Preparacion;
import tuti.desi.entidades.Producto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PreparacionServicioImpl implements PreparacionServicio {

    @Autowired
    private PreparacionRepositorio preparacionRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Autowired
    private ItemRecetaRepositorio itemRecetaRepositorio;


    @Override
    public Preparacion guardar(Preparacion preparacion) {

        if (preparacion.getFechaPreparacion().after(new Date())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }

        List<Preparacion> existentes = preparacionRepositorio.buscarPorFiltros(
                preparacion.getFechaPreparacion(),
                preparacion.getReceta().getNombre());
        for (Preparacion p : existentes) {
            if (p.getReceta().getId().equals(preparacion.getReceta().getId())) {
                throw new IllegalArgumentException("Ya existe una preparación de esta receta para la fecha indicada.");
            }
        }

        if (!hayStockSuficiente(preparacion)) {
            throw new IllegalArgumentException(
            		"No hay stock suficiente para preparar la cantidad de raciones indicadas.");
        }

        DarBajaStockIngrediente(preparacion);

        return preparacionRepositorio.save(preparacion);
    }


    @Override
    public List<Preparacion> listarTodas() {
        List<Preparacion> lista = preparacionRepositorio.findByEliminadoFalse();
        for (Preparacion p : lista) {
            Integer totalCalorias = itemRecetaRepositorio.obtenerCaloriasTotalesPorReceta(p.getReceta().getId());
            if (totalCalorias != null) {
                p.setCaloriasPorRacion(totalCalorias); 
            }
        }
        return lista;
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

    private boolean hayStockSuficiente(Preparacion preparacion) {
        List<ItemReceta> items = preparacion.getReceta().getIngredientes();
        int raciones = preparacion.getTotalRacionesPreparadas();
        for (ItemReceta item : items) {
            if (item.getIngrediente() instanceof Producto producto) {
                Double stockDisponible = producto.getStock();
                Double cantidadNecesaria = item.getCantidad() * raciones;
                if (stockDisponible < cantidadNecesaria) {
                    return false;
                }
            }
        }
        return true;
    }

    private void DarBajaStockIngrediente(Preparacion preparacion) {
        List<ItemReceta> items = preparacion.getReceta().getIngredientes();
        int raciones = preparacion.getTotalRacionesPreparadas();
        for (ItemReceta item : items) {
            if (item.getIngrediente() instanceof Producto producto) {
                Double cantidadNecesaria = item.getCantidad() * raciones;
                producto.setStock(producto.getStock() - cantidadNecesaria);
                productoRepositorio.save(producto);
            }
        }
    }
    
    @Override
    public Integer obtenerCaloriasTotalesPorReceta(Integer recetaId) {
        return itemRecetaRepositorio.obtenerCaloriasTotalesPorReceta(recetaId);
    }    

}