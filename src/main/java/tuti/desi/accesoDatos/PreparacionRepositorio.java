package tuti.desi.accesoDatos;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Preparacion;

@Repository
public interface PreparacionRepositorio extends JpaRepository<Preparacion, Integer> {

       
}