package tuti.desi.entidades;


import jakarta.persistence.*;
@Entity
public class Producto extends Ingrediente {


	
	
	private Float stock;
	
	private Float precio;
	
	
	public Float getStock() {
		return stock;
	}

	public void setStock(Float stock) {
		this.stock = stock;
	}
	
	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

}
