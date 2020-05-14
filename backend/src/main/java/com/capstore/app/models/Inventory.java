package com.capstore.app.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@Column(name = "inventory_id")
	private int invertoryId;
	@ElementCollection
	private List<Integer> merchantId;
	@ElementCollection
	private List<Integer> product_id;
	public int getInvertoryId() {
		return invertoryId;
	}
	public void setInvertoryId(int invertoryId) {
		this.invertoryId = invertoryId;
	}
	
	public List<Integer> getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(List<Integer> merchantId) {
		this.merchantId = merchantId;
	}
	public List<Integer> getProduct_id() {
		return product_id;
	}
	public void setProduct_id(List<Integer> product_id) {
		this.product_id = product_id;
	}
	public Inventory() {
	}
}
