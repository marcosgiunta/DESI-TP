package tuti.desi.accesoDatos;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import tuti.desi.entidades.Familia;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Integer> {

 // Método para buscar una familia por su número
 // Devuelve una lista de números de familia    

@Query("SELECT f.nroFamilia FROM Familia f")
List<Integer> findAllNroFamilia();

Familia findByNroFamilia(Integer nroFamilia);

}
