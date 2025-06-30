package tuti.desi.entidades;

import jakarta.persistence.*;



@Entity
public class Voluntario extends Persona {

    private Integer nroSeguro;


    public Integer getNroSeguro() {
        return nroSeguro;
    }

    public void setNroSeguro(Integer nroSeguro) {
        this.nroSeguro = nroSeguro;
    }




}
