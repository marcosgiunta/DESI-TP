package tuti.desi.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IngredientesRepositorio;
import tuti.desi.entidades.Ingrediente;

@Service
public class IngredientesServiceImpl implements IngredientesService {

    @Autowired
    private IngredientesRepositorio repo;

    @Override
    public Ingrediente guardar(Ingrediente ingrediente) {
        return repo.save(ingrediente);
    }

    @Override
    public Ingrediente buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}