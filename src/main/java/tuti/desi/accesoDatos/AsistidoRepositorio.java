package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Asistido;

@Repository 
public interface AsistidoRepositorio extends JpaRepository<Asistido, Integer> {

	Asistido findByDni(Integer dni);
}
