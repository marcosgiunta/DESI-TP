package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Persona;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

}
