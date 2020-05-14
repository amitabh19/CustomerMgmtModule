package com.capstore.app.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "customer_details")
public class CustomerDetails extends User {
	
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "alternate_phone_number")
    private String alternatePhoneNumber;
    @Column(name = "alternate_email")
    private String alternateEmail;
    @Column(name="address")
    private String address;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = CommonFeedback.class)
    Set<CommonFeedback> cCF;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ProductFeedback.class)
    Set<ProductFeedback> cPF;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Order.class)
	Set<Order> orders;
	 
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Cart.class)
    private Set<Cart> cC;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UserAddress.class)
    private Set<UserAddress> addresses;
    
    
    
    
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
	
	public Set<CommonFeedback> getcCF() {
		return cCF;
	}
	public void setcCF(Set<CommonFeedback> cCF) {
		this.cCF = cCF;
	}
	public Set<ProductFeedback> getcPF() {
		return cPF;
	}
	public void setcPF(Set<ProductFeedback> cPF) {
		this.cPF = cPF;
	}
	public Set<Cart> getcC() {
		return cC;
	}
	public void setcC(Set<Cart> cC) {
		this.cC = cC;
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
	@Override
	public String toString() {
		return "CustomerDetails [phoneNumber=" + phoneNumber + ", alternatePhoneNumber=" + alternatePhoneNumber
				+ ", alternateEmail=" + alternateEmail + ", address=" + address + ", cCF=" + cCF + ", cPF=" + cPF
				+ ", orders=" + orders + ", cC=" + cC + ", addresses=" + addresses + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
		result = prime * result + ((alternateEmail == null) ? 0 : alternateEmail.hashCode());
		result = prime * result + ((alternatePhoneNumber == null) ? 0 : alternatePhoneNumber.hashCode());
		result = prime * result + ((cC == null) ? 0 : cC.hashCode());
		result = prime * result + ((cCF == null) ? 0 : cCF.hashCode());
		result = prime * result + ((cPF == null) ? 0 : cPF.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetails other = (CustomerDetails) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (addresses == null) {
			if (other.addresses != null)
				return false;
		} else if (!addresses.equals(other.addresses))
			return false;
		if (alternateEmail == null) {
			if (other.alternateEmail != null)
				return false;
		} else if (!alternateEmail.equals(other.alternateEmail))
			return false;
		if (alternatePhoneNumber == null) {
			if (other.alternatePhoneNumber != null)
				return false;
		} else if (!alternatePhoneNumber.equals(other.alternatePhoneNumber))
			return false;
		if (cC == null) {
			if (other.cC != null)
				return false;
		} else if (!cC.equals(other.cC))
			return false;
		if (cCF == null) {
			if (other.cCF != null)
				return false;
		} else if (!cCF.equals(other.cCF))
			return false;
		if (cPF == null) {
			if (other.cPF != null)
				return false;
		} else if (!cPF.equals(other.cPF))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	
	

}
