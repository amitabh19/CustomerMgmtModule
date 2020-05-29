package com.capstore.app.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue
	@Column(name = "cart_id")
    private int cartId;   //(Primary Key)
	@Column(name = "type")
    private String type;  //(“Wishlist”,”cart”)
	
	@Column(name = "product_quantity")
	private int quantity;
	
	@Column(name="product_id")
	private int productId;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Cart(String type, int quantity, int productId) {
		//super();
		this.type = type;
		this.quantity = quantity;
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Cart() {
	}

}
