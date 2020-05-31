package com.capstore.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.exceptions.ResourceNotFoundException;
import com.capstore.app.models.CommonFeedback;
import com.capstore.app.models.CommonFeedback1;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.LocalCart;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.ProductFeedback1;
import com.capstore.app.repository.CustomerRepository;

import com.capstore.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerService productService;

	@RequestMapping("/customers")
	public List<CustomerDetails> getAllCustomers() {
		logger.trace("getAllCustomers Method Accessed...");
		return customerService.getAllCustomers();
	}

	@RequestMapping("/customer/{id}")
	public CustomerDetails getCustomerById(@PathVariable int id) {
		logger.trace("getCustomerById Method Accessed...");
		return customerService.getCustomerById(id);
	}

	@RequestMapping("/merchants")
	public List<MerchantDetails> getAllMerchants() {
		logger.trace("getAllMerchants Method Accessed...");
		return customerService.getAllMerchants();
	}

	@GetMapping("/merchant/{id}")
	public MerchantDetails getMerchantById(@PathVariable int id) {
		logger.trace("getMerchantById Method Accessed...");
		return customerService.getMerchantById(id);
	}

	@RequestMapping("products")
	public List<Product> getAllProducts() {
		logger.trace("getAllProducts Method Accessed...");
		return customerService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) throws ResourceNotFoundException {
		logger.trace("getProductById Method Accessed...");
		return customerService.getProductById(id);
	}

	@RequestMapping("productFeedback")
	public List<ProductFeedback> getAllProductFeedbacks() {
		logger.trace("getAllProductFeedbacks Method Accessed...");
		return customerService.getAllProductFeedbacks();
	}

	@GetMapping("/productFeedback/{id}")
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		logger.trace("getProductFeedbackById Method Accessed...");
		return customerService.getProductFeedbackById(id);
	}

	@GetMapping("/commonFeedback/{id}")
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		logger.trace("getCommonFeedbackById Method Accessed...");
		return customerService.getCommonFeedbackById(id);
	}

	@RequestMapping("commonFeedback")
	public List<CommonFeedback> getAllCommonFeedbacks() {
		logger.trace("getAllCommonFeedbacks Method Accessed...");
		return customerService.getAllCommonFeedbacks();
	}

	@RequestMapping("/orders")
	public List<Order> getAllOrders() {
		logger.trace("getAllOrders Method Accessed...");
		return customerService.getAllOrders();
	}

	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable int id) {
		logger.trace("getOrderById Method Accessed...");
		return customerService.getOrderById(id);
	}

	// function to get numbers from json
	public int stringCleaner(String obj) {
		logger.trace("stringCleaner Method Accessed...");
		String[] arr1 = obj.split("=");
		String q = arr1[1];
		int quan = Integer.parseInt(q);
		return quan;
	}

	// post function to add to cart
	@PostMapping("/atC")
	public CustomerDetails addToCartBC(@RequestBody LocalCart lc) {
		// LocalCart : [uid =1, pid=2, quan=3]
		logger.trace("addToCartBC Method Accessed...");
		return customerService.addToCartBC(lc);

	}

	@GetMapping("/cartProducts/{id}")
	public List<Product> cartProducts(@PathVariable int id) {
		logger.trace("cartProducts Method Accessed...");
		return customerService.cartProducts(id);

	}

	@GetMapping("/wishProducts/{id}")
	public List<Product> wishProducts(@PathVariable int id) {
		logger.trace("wishProducts Method Accessed...");
		return customerService.wishProducts(id);

	}

	// post function to add to wishlist
	@PostMapping("/atW")
	public CustomerDetails addToWishlist(@RequestBody LocalCart lc) {
		logger.trace("addToWishlist Method Accessed...");
		return customerService.addToWishlist(lc);

	}

	// function to delete wishlist
	@DeleteMapping("/atDFW/{id}")
	public String addToCartFromWishList(@PathVariable int id) {
		logger.trace("addToCartFromWishList Method Accessed...");
		return customerService.addToCartFromWishList(id);
	}

	// function to delete from cart
	@DeleteMapping("/atDFC/{id}")
	public String deleteFromCart(@PathVariable int id) {
		logger.trace("deleteFromCart Method Accessed...");
		return customerService.deleteFromCart(id);
	}

	// put function to add to cart
	@PutMapping("/atC")
	public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		logger.trace("addToCartPut Method Accessed...");
		return customerService.addToCartPut(c);

	}

	// function to get customer details
	@GetMapping("/customerdetails/{id}")
	public CustomerDetails getCustomerDetailById(@PathVariable Integer id) throws ResourceNotFoundException {
		logger.trace("getCustomerDetailById Method Accessed...");
		return customerService.getCustomerDetailsById(id);
	}

	// function to update customer details
	@PutMapping("/updateCustomerDetails")
	public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails) {
		logger.trace("updateCustomerDetails Method Accessed...");
		return customerService.updateCustomerDetails(custDetails);
	}
	
	@GetMapping("/productIdByName/{name}")
	public int getProductIdByName(@PathVariable String name) {
		logger.trace("getProductIdByName Method Accessed...");
		return productService.ListProductIdByName(name);
	}
	
	@RequestMapping("/categories")
	public List<String> categories() {
		logger.trace("categories Method Accessed...");
		return productService.categories();
	}

	@RequestMapping("/categories/{c}")
	public List<Product> productByCategory(@PathVariable String c) {
		logger.trace("productByCategory Method Accessed...");
		return productService.productByCategory(c);
	}
	

	// function to add product feedback
	@PostMapping("/addFeedback")
	public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
		logger.trace("addFeedback Method Accessed...");
		return customerService.create(productFeedback);
	}

	// function to add common feedback
	@PostMapping("/addCommonFeedback")
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {
		logger.trace("createCommonFeedback Method Accessed...");
		return customerService.createCommonFeedback(commonFeedback);

	}
	
	// function to get name of product ordered by customer
	@GetMapping("/orderedProductName/{id}")
	public List<String> getOrderedProductName(@PathVariable int id) {
		logger.trace("getOrderedProductName Method Accessed...");
		return customerService.getOrderedProductName(id);
	}
	
	//function to get product name by using product id
	@GetMapping("/productNameById/{id}")
	public String getNameByProductId(@PathVariable int id) {
		logger.trace("getNameByProductId Method Accessed...");
		return customerService.getNameByProductId(id);
	}

}
