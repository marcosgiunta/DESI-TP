package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.EntregaAsistencia;

@Repository
public interface EntregaAsistenciaRepositorio extends JpaRepository<EntregaAsistencia, Integer>{

}
