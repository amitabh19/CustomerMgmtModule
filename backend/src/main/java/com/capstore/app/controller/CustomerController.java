package com.capstore.app.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.models.Cart;
import com.capstore.app.models.CommonFeedback;
import com.capstore.app.models.CommonFeedback1;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.LocalCart;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.ProductFeedback1;
import com.capstore.app.repository.CartRepository;
import com.capstore.app.repository.CommonFeedbackRepository;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.OrderRepository;
import com.capstore.app.repository.ProductFeedbackRepository;
import com.capstore.app.repository.ProductRepository;

import com.capstore.app.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductFeedbackRepository productFeedbackRepository;

	@Autowired
	private CommonFeedbackRepository commonFeedbackRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerService productService;

	@RequestMapping("/customers")
	public List<CustomerDetails> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@RequestMapping("/customer/{id}")
	public CustomerDetails getCustomerById(@PathVariable int id) {
		return customerService.getCustomerById(id);
	}

	@RequestMapping("/merchants")
	public List<MerchantDetails> getAllMerchants() {
		return customerService.getAllMerchants();
	}

	@GetMapping("/merchant/{id}")
	public MerchantDetails getMerchantById(@PathVariable int id) {
		return customerService.getMerchantById(id);
	}

	@RequestMapping("products")
	public List<Product> getAllProducts() {
		return customerService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return customerService.getProductById(id);
	}

	@RequestMapping("productFeedback")
	public List<ProductFeedback> getAllProductFeedbacks() {
		return customerService.getAllProductFeedbacks();
	}

	@GetMapping("/productFeedback/{id}")
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		return customerService.getProductFeedbackById(id);
	}

	@GetMapping("/commonFeedback/{id}")
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		return customerService.getCommonFeedbackById(id);
	}

	@RequestMapping("commonFeedback")
	public List<CommonFeedback> getAllCommonFeedbacks() {
		return customerService.getAllCommonFeedbacks();
	}

	@RequestMapping("/orders")
	public List<Order> getAllOrders() {
		return customerService.getAllOrders();
	}

	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable int id) {
		return customerService.getOrderById(id);
	}

	// function to get numbers from json
	public int stringCleaner(String obj) {
		String[] arr1 = obj.split("=");
		String q = arr1[1];
		int quan = Integer.parseInt(q);
		return quan;
	}

	// post function to add to cart
	@PostMapping("/atC")
	public CustomerDetails addToCartBC(@RequestBody LocalCart lc) {
		// LocalCart : [uid =1, pid=2, quan=3]

		return customerService.addToCartBC(lc);

	}

	@GetMapping("/cartProducts/{id}")
	public List<Product> cartProducts(@PathVariable int id) {
		
		return customerService.cartProducts(id);

	}
	
	@GetMapping("/wishProducts/{id}")
	public List<Product> wishProducts(@PathVariable int id) {
		
		return customerService.wishProducts(id);

	}

	// post function to add to wishlist
	@PostMapping("/atW")
	public CustomerDetails addToWishlist(@RequestBody LocalCart lc) {

		return customerService.addToWishlist(lc);

	}

	// function to delete wishlist
	@DeleteMapping("/atDFW/{id}")
	public String addToCartFromWishList(@PathVariable int id) {
		
		return customerService.addToCartFromWishList(id);
	}

	// function to delete from cart
	@DeleteMapping("/atDFC/{id}")
	public String deleteFromCart(@PathVariable int id) {
		
		return customerService.deleteFromCart(id);
	}

	// put function to add to cart
	@PutMapping("/atC")
	public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		return customerService.addToCartPut(c);

	}
		
		// function for product feedback
		@PostMapping("/addFeedback")
		public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
			//System.out.println("The product feedback object is: " + productFeedback.toString());
			
			return  customerService.create(productFeedback);
		}

		
		//Nikhil
		@GetMapping("/customerdetails/{id}")
		public CustomerDetails getCustomerDetailById(@PathVariable Integer id)
		{
			return customerService.getCustomerDetailsById(id);
		}
		
		//Nikhil
		@PutMapping("/updateCustomerDetails")
		public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails)
		{
			return customerService.updateCustomerDetails(custDetails);
		}
		
	
		
	//function for common feedback
	@PostMapping("/addCommonFeedback")
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {
		System.out.println("The common feedback object is: " + commonFeedback.toString());
		
		int custId=commonFeedback.userId;
		String feedSubject=commonFeedback.feedbackSubject;
		String feedMessage=commonFeedback.feedbackMessage;
		int productId=commonFeedback.productId;
		
		boolean requestFlag = true;
		boolean responseFlag = false;
		boolean requestApproved = false;
		boolean responseApproved = false;
		String responseMessage = null;

		CommonFeedback cf = new CommonFeedback(feedSubject, feedMessage, productId, requestFlag, responseFlag, requestApproved,
				responseApproved, responseMessage);
		Set<CommonFeedback> cfset = new HashSet<CommonFeedback>();
		cfset.add(cf);

		System.out.println(cfset);

		CustomerDetails c = customerRepository.getOne(custId);
		Set<CommonFeedback> res = c.getFeedbacks();
		res.add(cf);
		c.setFeedbacks(res);
		System.out.println(res);

		return customerService.createCommonFeedback(commonFeedback);

	}

	@RequestMapping("/product1/{name}")
	public Product getProductByName(@PathVariable String name) {
		return productService.ListProductsByName(name);
	}
	
	@GetMapping("/productIdByName/{name}")
	public int getProductIdByName(@PathVariable String name) {
		return productService.ListProductIdByName(name);
	}
	
	@GetMapping("/orderedProductName/{id}")
	public List<String> getOrderedProductName(@PathVariable int id) {
		
		return customerService.getOrderedProductName(id);
	}
	
	@GetMapping("/productNameById/{id}")
	public String getNameByProductId(@PathVariable int id) {
		return customerService.getNameByProductId(id);
	}
	
	

	@RequestMapping("/categories")
	public List<String> categories() {
		List<Product> p = productService.ListProducts();
		List<String> categories = new ArrayList<>();
		for (Product p1 : p) {
			categories.add(p1.getProductCategory());
		}
		Set<String> f1 = new HashSet<>();
		for (String s : categories) {
			f1.add(s);
		}

		List<String> categories1 = new ArrayList<>();
		for (String s : f1) {
			categories1.add(s);
		}

		System.out.println(categories1.toString());
		return categories1;

	}

	@RequestMapping("/categories/{c}")
	public List<Product> productByCategory(@PathVariable String c) {
		List<Product> p1 = productService.ListProducts().stream().filter(n -> n.getProductCategory().equals(c))
				.collect(Collectors.toList());
		return p1;
	}

	@RequestMapping("/productsName")
	public List<String> getName() {
		List<String> names = new ArrayList<>();
		List<Product> p1 = productService.ListProducts();

		for (Product p : p1) {
			names.add(p.getProductName());
		}

		System.out.println(names.toString());
		return names;
	}

	@RequestMapping("test")
	public Product addProduct() {
		Product p = new Product("Product1", "./assets/image3.jpg", 500, 4, 2, "B1", 100, "Wired Earphones", 2,
				"Mobile Accessories", true, true, true);
		Product p1 = productService.addProduct(p);
		return p1;
	}

}
