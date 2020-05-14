package com.capstore.app.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "merchant_details")
public class MerchantDetails extends User {

	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "alternate_phone_number")
	private String alternatePhoneNumber;
	@Column(name = "alternate_email")
	private String alternateEmail;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Product.class)
	private Set<Product> products;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UserAddress.class)
	private Set<UserAddress> addresses;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ProductFeedback.class)
	private Set<ProductFeedback> pF;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Coupon.class)
	Set<Coupon> coupons;
	@Column(name = "is_approved")
	private boolean isApproved;
	@Column(name = "rating")
	private int rating;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Inventory.class)
	private int inventoryId;

	public MerchantDetails(String name, String username, String password, String eMail, String role,
			boolean isActive, String securityQuestion, String securityAnswer, String phoneNumber,
			String alternatePhoneNumber, String alternateEmail, Set<Product> products, Set<UserAddress> addresses,
			Set<ProductFeedback> pF, Set<Coupon> coupons, boolean isApproved, int rating, int inventoryId) {
		super(name, username, password, eMail, role, isActive, securityQuestion, securityAnswer);
		this.phoneNumber = phoneNumber;
		this.alternatePhoneNumber = alternatePhoneNumber;
		this.alternateEmail = alternateEmail;
		this.products = products;
		this.addresses = addresses;
		this.pF = pF;
		this.coupons = coupons;
		this.isApproved = isApproved;
		this.rating = rating;
		this.inventoryId = inventoryId;
	}

	public Set<ProductFeedback> getpF() {
		return pF;
	}

	public void setpF(Set<ProductFeedback> pF) {
		this.pF = pF;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<UserAddress> addresses) {
		this.addresses = addresses;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<ProductFeedback> getProductFeedback() {
		return pF;
	}

	public void setProductFeedback(Set<ProductFeedback> productFeedback) {
		this.pF = productFeedback;
	}

	public MerchantDetails() {
	}

}
