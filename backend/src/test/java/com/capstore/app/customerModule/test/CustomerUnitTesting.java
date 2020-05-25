package com.capstore.app.customerModule.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capstore.app.controller.CustomerController;
import com.capstore.app.models.Cart;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.LocalCart;
import com.capstore.app.models.Product;
import com.capstore.app.repository.CustomerRepository;



@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class CustomerUnitTesting {

	@Autowired
	private CustomerController customerController;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	//test functions for cart module
	
	//test function for add to cart
	@Test
	@Rollback(true)
	public void testAddToCart() {
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);
		
		CustomerDetails cd=	customerController.addToCartBC(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for(Cart c:cartSet) {
			if(c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				checked = true;
			}
		}
		
		Assert.assertEquals(true,checked);
	}
	
	
	//test function for add to wishlist
	@Test
	@Rollback(true)
	public void addToWishList() {
		
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);
		
		CustomerDetails cd=	customerController.addToWishlist(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for(Cart c:cartSet) {
			if(c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				checked = true;
			}
		}
		
		Assert.assertEquals(true,checked);
	}
	
	//test function to get cart list
	@Test
	@Rollback(true)
	public void testCartProducts() {
		
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);
		
		customerController.addToCartBC(lc);
		
		List<Product> wishList = customerController.cartProducts(3);
		Assert.assertNotNull(wishList);
	}
	
	//test function to get wishList
	@Test
	@Rollback(true)
	public void testWishProducts() {
		
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);
		
		customerController.addToWishlist(lc);
		
		List<Product> wishList = customerController.wishProducts(3);
		Assert.assertNotNull(wishList);
	}
	
	//test function to delete cart product
	@Test
	@Rollback(true)
	public void testDeleteWishList() {
		
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);
		
		CustomerDetails cd=	customerController.addToWishlist(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for(Cart c:cartSet) {
			if(c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				customerController.addToCartFromWishList(c.getCartId());
				checked = true;
				
			}
		}
		Assert.assertEquals(true, checked);
		
	}
	
	//test function to delete cart product
	@Test
	@Rollback(true)
	public void testDeleteCartProduct() {
		
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);
		
		CustomerDetails cd=	customerController.addToCartBC(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for(Cart c:cartSet) {
			if(c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				customerController.deleteFromCart(c.getCartId());
				checked = true;
				
			}
		}
		Assert.assertEquals(true, checked);
		
	}
	
	//test function to get customer details by id
	@Test
	@Rollback(true)
	public void testGetCustomerDetailById() {
		CustomerDetails customerDetail = new CustomerDetails("Roxane", "roxy", "roxy3996", "roxy@gmail.com", "User", true, "What is your petname", "Roxy", "3456789012", "2345678901", "rox@gmail.com", "India", null, null, null, null, null);
		customerRepository.save(customerDetail);
		CustomerDetails found=null;
		found = customerController.getCustomerDetailById(customerDetail.getUserId());
		assertThat(found).isEqualTo(customerDetail);
	}
	
	//test function to update customer details
	@Test
	@Rollback(true)
	public void testUpdateCustomerDetails() {
		CustomerDetails customerDetail = new CustomerDetails("Roxane", "roxy", "roxy3996", "roxy@gmail.com", "User", true, "What is your petname", "Roxy", "3456789012", "2345678901", "rox@gmail.com", "India", null, null, null, null, null);
		customerRepository.save(customerDetail);
		customerDetail.setName("Roxxane");
		customerRepository.save(customerDetail);
		assertThat(customerDetail.getName()).isEqualTo("Roxxane");
	}
	
}
