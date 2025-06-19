package tuti.desi.entidades;


import jakarta.persistence.*;
@Entity
public class Producto extends Ingrediente {


	
	
	private float stock;
	
	private float precio;
	
	
	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
