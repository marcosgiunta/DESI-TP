package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.desi.entidades.vistaUltimaEntega;

@Repository
public interface VistaFechasEntregasRespositorio extends JpaRepository<vistaUltimaEntega, Integer> {
}
