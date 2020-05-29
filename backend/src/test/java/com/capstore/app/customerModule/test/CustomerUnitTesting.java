package com.capstore.app.customerModule.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.capstore.app.models.CommonFeedback;
import com.capstore.app.models.CommonFeedback1;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.LocalCart;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.ProductFeedback1;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.ProductRepository;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
class CustomerUnitTesting {

	@Autowired
	private CustomerController customerController;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	// test functions for cart module

	// test function for add to cart
	@Test
	@Rollback(true)
	public void testAddToCart() {
		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);

		CustomerDetails cd = customerController.addToCartBC(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for (Cart c : cartSet) {
			if (c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				checked = true;
			}
		}

		Assert.assertEquals(true, checked);
	}

	// test function for add to wishlist
	@Test
	@Rollback(true)
	public void addToWishList() {

		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);

		CustomerDetails cd = customerController.addToWishlist(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for (Cart c : cartSet) {
			if (c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				checked = true;
			}
		}

		Assert.assertEquals(true, checked);
	}

	// test function to get cart list
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

	// test function to get wishList
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

	// test function to delete cart product
	@Test
	@Rollback(true)
	public void testDeleteWishList() {

		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);

		CustomerDetails cd = customerController.addToWishlist(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for (Cart c : cartSet) {
			if (c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				customerController.addToCartFromWishList(c.getCartId());
				checked = true;

			}
		}
		Assert.assertEquals(true, checked);

	}

	// test function to delete cart product
	@Test
	@Rollback(true)
	public void testDeleteCartProduct() {

		Product p = customerController.getProductById(22);
		LocalCart lc = new LocalCart();
		lc.setCid(3);
		lc.setPid(p);
		lc.setQuantity(3);

		CustomerDetails cd = customerController.addToCartBC(lc);
		Set<Cart> cartSet = cd.getCustomerCarts();
		boolean checked = false;
		for (Cart c : cartSet) {
			if (c.getProductId() == lc.getPid().getProductId() && c.getQuantity() == lc.getQuantity()) {
				customerController.deleteFromCart(c.getCartId());
				checked = true;

			}
		}
		Assert.assertEquals(true, checked);

	}

	// test function to get customer details by id
	@Test
	@Rollback(true)
	public void testGetCustomerDetailById() {
		CustomerDetails customerDetail = new CustomerDetails("Roxane", "roxy", "roxy3996", "roxy@gmail.com", "User",
				true, "What is your petname", "Roxy", "3456789012", "2345678901", "rox@gmail.com", "India", null, null,
				null, null, null);
		customerRepository.save(customerDetail);
		CustomerDetails found = null;
		found = customerController.getCustomerDetailById(customerDetail.getUserId());
		assertThat(found).isEqualTo(customerDetail);
	}

	// test function to update customer details
	@Test
	@Rollback(true)
	public void testUpdateCustomerDetails() {
		CustomerDetails customerDetail = new CustomerDetails("Roxane", "roxy", "roxy3996", "roxy@gmail.com", "User",
				true, "What is your petname", "Roxy", "3456789012", "2345678901", "rox@gmail.com", "India", null, null,
				null, null, null);
		customerRepository.save(customerDetail);
		customerDetail.setName("Roxxane");
		customerRepository.save(customerDetail);
		assertThat(customerDetail.getName()).isEqualTo("Roxxane");
	}

	// Test function to add new Product Feedback
	@Test
	@Rollback(true)
	public void addProductFeedback() {

		ProductFeedback1 pfTest = new ProductFeedback1();
		pfTest.setUserId(3);
		pfTest.setFeedbackSubject("This is a test product feedback subject");
		pfTest.setFeedbackMessage("This is a test product feedback message");
		pfTest.setProductId(20);

		CustomerDetails cd = customerController.create(pfTest);
		Set<ProductFeedback> productFeedbackSet = cd.getProductFeedbacks();
		boolean checked = false;
		for (ProductFeedback pf : productFeedbackSet) {
			if (pf.getProductId() == pfTest.getProductId() && pf.getFeedbackSubject() == pfTest.getFeedbackSubject()
					&& pf.getFeedbackMessage() == pfTest.getFeedbackMessage()) {
				checked = true;
			}
		}
		Assert.assertEquals(true, checked);
	}

	// Test function to add new Common Feedback
	@Test
	@Rollback(true)
	public void addCommonFeedback() {

		CommonFeedback1 cfTest = new CommonFeedback1();
		cfTest.setUserId(3);
		cfTest.setFeedbackSubject("This is a test common feedback subject");
		cfTest.setFeedbackMessage("This is a test common feedback message");
		cfTest.setProductId(20);

		CustomerDetails cd = customerController.createCommonFeedback(cfTest);
		Set<CommonFeedback> commonFeedbackSet = cd.getFeedbacks();
		boolean checked = false;
		for (CommonFeedback cf : commonFeedbackSet) {
			if (cf.getProductId() == cfTest.getProductId() && cf.getFeedbackSubject() == cfTest.getFeedbackSubject()
					&& cf.getFeedbackMessage() == cfTest.getFeedbackMessage()) {
				checked = true;
			}
		}
		Assert.assertEquals(true, checked);
	}

	// Test function to get name of products ordered by the customer using customer
	// user_id
	@Test
	@Rollback(true)
	public void getOrderedProductName() {

		List<String> sampleTest = new ArrayList<String>();
		sampleTest.add("Product2");
		sampleTest.add("Product6");
		sampleTest.add("Product4");
		sampleTest.add("Product10");
		sampleTest.add("Product8");

		List<String> result = customerController.getOrderedProductName(3);
		boolean checked = false;
		if (sampleTest.equals(result))
			checked = true;
		Assert.assertEquals(true, checked);
	}

	// Test function to get object of product by using its name
	@Test
	public void getProductByName() {
		String name = "Product2";
		List<Product> list1 = productRepository.findAll();
		List<Product> list2 = list1.stream().filter(n -> n.getProductName().equals(name)).collect(Collectors.toList());
		Product p1 = list2.get(0);
		Product p2 = customerController.getProductByName(name);

		Assert.assertEquals(p2, p1);
	}

	// Test to get product id by its name
	@Test
	public void getProductIdByName() {
		String name = "Product2";
		int p1 = customerController.getProductIdByName(name);
		List<Product> list1 = productRepository.findAll();
		List<Product> list2 = list1.stream().filter(n -> n.getProductName().equals(name)).collect(Collectors.toList());
		Product p2 = list2.get(0);
		Assert.assertEquals(p1, p2.getProductId());
	}

	// Test to get names of all categories of products available
	@Test
	public void categories() {
		List<String> s1 = customerController.categories();
		List<Product> p1 = productRepository.findAll();
		List<String> s2 = new ArrayList<>();
		for (Product p2 : p1) {
			s2.add(p2.getProductCategory());
		}
		Set<String> f1 = new HashSet<>();
		for (String s : s2) {
			f1.add(s);
		}

		List<String> categories1 = new ArrayList<>();
		for (String s : f1) {
			categories1.add(s);
		}

		Assert.assertEquals(categories1, s1);
	}

	// Test to get products based on categories
	@Test
	public void productByCategory() {
		String c = "Category1";
		List<Product> list1 = customerController.productByCategory(c);
		List<Product> list2 = productRepository.findAll().stream().filter(n -> n.getProductCategory().equals(c))
				.collect(Collectors.toList());
		Assert.assertEquals(list2, list1);
	}

	// Test to get names of all products
	@Test
	public void getName() {
		List<String> list1 = customerController.getName();
		List<String> list2 = new ArrayList<>();
		List<Product> p = productRepository.findAll();
		for (Product p1 : p) {
			list2.add(p1.getProductName());
		}
		Assert.assertEquals(list2, list1);
	}

}
