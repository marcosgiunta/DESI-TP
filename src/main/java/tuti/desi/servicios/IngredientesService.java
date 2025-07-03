package tuti.desi.servicios;

import java.util.List;
import tuti.desi.entidades.Ingrediente;

public interface IngredientesService {
    Ingrediente guardar(Ingrediente ingrediente);
    Ingrediente buscarPorId(Integer id);
    void eliminar(Integer id);
}
