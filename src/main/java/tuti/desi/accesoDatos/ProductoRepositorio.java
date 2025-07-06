package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tuti.desi.entidades.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{
}
