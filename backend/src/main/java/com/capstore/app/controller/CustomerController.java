package com.capstore.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.models.Cart;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	//random comment
	// krapiya yha apne functions bana dijiye
	//mike testing 
	
	@Autowired 
	private ProductRepository productRepository;
	

	@Autowired
	private MerchantRepository merchantRepository;
	
	@RequestMapping("/customers")
	public List<CustomerDetails> getAllCustomers() {
		return customerRepository.findAll(); 
	}
	
	@RequestMapping("/customer/{id}")
	public CustomerDetails getCustomerById(@PathVariable int id) {
		return customerRepository.getOne(id);
	}
	
	@RequestMapping("/merchants")
	public List<MerchantDetails> getAllMerchants(){
		return merchantRepository.findAll();
	}
	
	@GetMapping("/merchant/{id}")
	public MerchantDetails getMerchantById(@PathVariable int id) {
		return merchantRepository.getOne(id);
	}
	
	@RequestMapping("products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("product/{id}")
	public Product getProductById(@PathVariable int id) {
		return productRepository.getOne(id);
	}
	
	//function to get numbers from json
	public int stringCleaner(String obj) {
		String[] arr1 = obj.split("=");
		String q = arr1[1];
		int quan = Integer.parseInt(q);
		return quan;
	}
	
	//post function to add to cart
	@PostMapping("/atC")
	public CustomerDetails addToCart(@RequestBody Object object) {

		String str = object.toString();
		String str1 = str.replace("{", "");
		String str2 = str1.replace("}", "");
		String[] array = str2.split(",");
		int quan = stringCleaner(array[0]);
		int pid = stringCleaner(array[1]);
		int cid = stringCleaner(array[2]);
		//String[] t1 = array[3].split("=");
		//String type = t1[1];
		CustomerDetails c = customerRepository.getOne(cid);
		Product p = productRepository.getOne(pid);
		Set<Cart> cart = c.getCustomerCarts();

		for (Cart t : cart) {
			System.out.println("The type of cart is:"+t.getType());
			if (t.getProductId() == p.getProductId() && t.getType().equals("cart")) {
				t.setQuantity(t.getQuantity() + quan);
				customerRepository.save(c);
				return customerRepository.save(c);
			}
		}

		Cart ct = new Cart("cart", quan, p.getProductId());
		cart.add(ct);
		c.setCustomerCarts(cart);
		customerRepository.save(c);
		return customerRepository.save(c);

	}
	
	
	
	//post function to add to wishlist 
	@PostMapping("/atW")
	public CustomerDetails addToWishlist(@RequestBody Object object) {

		String str = object.toString();
		String str1 = str.replace("{", "");
		String str2 = str1.replace("}", "");
		String[] array = str2.split(",");
		int quan = stringCleaner(array[0]);
		int pid = stringCleaner(array[1]);
		int cid = stringCleaner(array[2]);

		CustomerDetails c = customerRepository.getOne(cid);
		Product p = productRepository.getOne(pid);
		Set<Cart> cart = c.getCustomerCarts();

		for (Cart t : cart) {
			if (t.getProductId() == p.getProductId() && t.getType().equals("wishlist")) {
				t.setQuantity(t.getQuantity() + quan);
				customerRepository.save(c);
				return customerRepository.save(c);
			}
		}

		Cart ct = new Cart("wishlist", quan, p.getProductId());
		cart.add(ct);
		c.setCustomerCarts(cart);
		customerRepository.save(c);
		return customerRepository.save(c);

	}

	
	 //put function to add to cart
	  @PutMapping("/atC")
	  public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		  return customerRepository.save(c);
		  
	  }
	  
	  // function for product feedback
	  @PostMapping("/addFeedback")
		public CustomerDetails create(@RequestBody Object object) {
		 // System.out.println(uid);
		  System.out.println("The product feedback object is: "+object.toString());
		  
		  String str = object.toString();
			String str1 = str.replace("{", "");
			String str2 = str1.replace("}", "");
			String[] array = str2.split(",");
			
			//to get user id
			String[] ar = array[0].split("=");
			int id = Integer.parseInt(ar[1]);
			System.out.println(id);
			
			//to get subject
			String[] ar1 = array[1].split("=");
			String subject= ar1[1];
			System.out.println(subject);
			
			//to get message
			String[] ar2 = array[2].split("=");
			String message= ar2[1];
			System.out.println(message);
			
			//to get product id
			String[] ar3 = array[3].split("=");
			int pid = Integer.parseInt(ar3[1]);
			System.out.println(pid);
			
			
			ProductFeedback pf = new ProductFeedback(subject, message, pid);
			Set<ProductFeedback> pfset = new HashSet<ProductFeedback>();
			pfset.add(pf);
			
			CustomerDetails c = customerRepository.getOne(id);
			c.setProductFeedbacks(pfset);
			
		  
		  return customerRepository.save(c);
		  
		}		
}

