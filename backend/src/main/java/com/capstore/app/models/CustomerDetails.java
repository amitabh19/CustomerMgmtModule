package com.capstore.app.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "customer_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDetails extends User {
	
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "alternate_phone_number")
    private String alternatePhoneNumber;
    @Column(name = "alternate_email")
    private String alternateEmail;
    @Column(name="address")
    private String address;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    Set<CommonFeedback> cCF;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    Set<ProductFeedback> cPF;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    Set<Order> orders;
	 
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Set<Cart> cC;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Set<UserAddress> addresses;
    
	
	public CustomerDetails(String name, String username, String password, String eMail, String role, boolean isActive,
			String securityQuestion, String securityAnswer, String phoneNumber, String alternatePhoneNumber,
			String alternateEmail, String address, Set<CommonFeedback> cCF, Set<ProductFeedback> cPF, Set<Order> orders,
			Set<Cart> cC, Set<UserAddress> addresses) {
		super(name, username, password, eMail, role, isActive, securityQuestion, securityAnswer);
		this.phoneNumber = phoneNumber;
		this.alternatePhoneNumber = alternatePhoneNumber;
		this.alternateEmail = alternateEmail;
		this.address = address;
		this.cCF = cCF;
		this.cPF = cPF;
		this.orders = orders;
		this.cC = cC;
		this.addresses = addresses;
	}
	
	
	
	
	public CustomerDetails(int userId, String name, String username, String password, String eMail, String role,
			boolean isActive, String securityQuestion, String securityAnswer, String phoneNumber,
			String alternatePhoneNumber, String alternateEmail, String address, Set<CommonFeedback> cCF,
			Set<ProductFeedback> cPF, Set<Order> orders, Set<Cart> cC, Set<UserAddress> addresses) {
		super(userId, name, username, password, eMail, role, isActive, securityQuestion, securityAnswer);
		this.phoneNumber = phoneNumber;
		this.alternatePhoneNumber = alternatePhoneNumber;
		this.alternateEmail = alternateEmail;
		this.address = address;
		this.cCF = cCF;
		this.cPF = cPF;
		this.orders = orders;
		this.cC = cC;
		this.addresses = addresses;
	}




	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<Cart> getCustomerCarts() {
		return cC;
	}
	public void setCustomerCarts(Set<Cart> customerCarts) {
		this.cC = customerCarts;
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
	
	public Set<CommonFeedback> getFeedbacks() { return cCF; } 
	public void setFeedbacks(Set<CommonFeedback> feedbacks) { this.cCF = feedbacks; }
	
	Set<UserAddress> getAddresses() { return addresses; } 
	public void setAddresses(Set<UserAddress> addresses) { this.addresses = addresses; }
	
	public Set<ProductFeedback> getProductFeedbacks() { return cPF;} 
	public void setProductFeedbacks(Set<ProductFeedback> productFeedbacks) { this.cPF = productFeedbacks; }
	
	public CustomerDetails() {
	}
	
	

}
