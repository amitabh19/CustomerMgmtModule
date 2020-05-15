package com.capstore.app.models;

import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="order_amount")
	private double orderAmount;
	
	
	@Column(name="order_status")
	private String orderStatus;
	
	@ElementCollection
	@MapKeyColumn(name = "product_id")
	@CollectionTable(joinColumns = {@JoinColumn(name="user_id")})
	private Map<Integer, Integer> products;
	
	
	
	@Column(name="address_id")
	private int addressId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	private Set<Transaction> transaction;
	

	public Order(double orderAmount, String orderStatus, Map<Integer, Integer> products, int addressId,
			Set<Transaction> transaction) {
		super();
		this.orderAmount = orderAmount;
		this.orderStatus = orderStatus;
		this.products = products;
		this.addressId = addressId;
		this.transaction = transaction;
	}

	public Set<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Map<Integer, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer, Integer> products) {
		this.products = products;
	}

	

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
		
}
