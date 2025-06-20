package tuti.desi.entidades;

import jakarta.persistence.*;



@Entity
public class Voluntario extends Persona {

    private int nroSeguro;


    public int getNroSeguro() {
        return nroSeguro;
    }

    public void setNroSeguro(int nroSeguro) {
        this.nroSeguro = nroSeguro;
    }




}
