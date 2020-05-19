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
import com.capstore.app.models.CommonFeedback;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.repository.CommonFeedbackRepository;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.OrderRepository;
import com.capstore.app.repository.ProductFeedbackRepository;
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
	private ProductFeedbackRepository productFeedbackRepository;
	
	@Autowired 
	private CommonFeedbackRepository commonFeedbackRepository;

	@Autowired 
	private OrderRepository orderRepository;

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
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return productRepository.getOne(id);
	}
	
	@RequestMapping("productFeedback")
	public List<ProductFeedback> getAllProductFeedbacks(){
		return productFeedbackRepository.findAll();
	}
	
	@GetMapping("/productFeedback/{id}")
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		return productFeedbackRepository.getOne(id);
	}
	
	@GetMapping("/commonFeedback/{id}")
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		return commonFeedbackRepository.getOne(id);
	}
	
	@RequestMapping("commonFeedback")
	public List<CommonFeedback> getAllCommonFeedbacks(){
		return commonFeedbackRepository.findAll();
	}
	
	@RequestMapping("/orders")
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable int id) {
		return orderRepository.getOne(id);
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
			
			System.out.println(pfset);

			CustomerDetails c = customerRepository.getOne(id);
			Set<ProductFeedback> res=c.getProductFeedbacks();
			res.add(pf);
			c.setProductFeedbacks(res);
			System.out.println(res);
		  
		  return customerRepository.save(c);
		  
		}		
	  
	  @PostMapping("/addCommonFeedback")
		public CustomerDetails createCommonFeedback(@RequestBody Object object) {
		  System.out.println("The common feedback object is: "+object.toString());
		  
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
			
			boolean requestFlag=true;
			boolean responseFlag=false;
			boolean requestApproved=false;
			boolean responseApproved=false;
			String responseMessage=null;
			
			
			CommonFeedback cf = new CommonFeedback(subject, message, pid,requestFlag,responseFlag,requestApproved,responseApproved, responseMessage );
			Set<CommonFeedback> cfset = new HashSet<CommonFeedback>();
			cfset.add(cf);
			
			System.out.println(cfset);

			CustomerDetails c = customerRepository.getOne(id);
			Set<CommonFeedback> res=c.getFeedbacks();
			res.add(cf);
			c.setFeedbacks(res);
			System.out.println(res);
			
			
		  
		  return customerRepository.save(c);
		  
		}		
	  @RequestMapping("/product/{name}")
		public Product getProductByName(@PathVariable String name)
		{
			return productService.ListProductsByName(name);
		}
		
		@RequestMapping("/categories")
		public List<String> categories()
		{
			List<Product> p=productService.ListProducts();
			List<String> categories=new ArrayList<>();
			for(Product p1:p)
			{
				categories.add(p1.getProductCategory());
			}
			Set<String> f1=new HashSet<>();
			for(String s:categories)
			{
				f1.add(s);
			}
			
			List<String> categories1=new ArrayList<>();
			for(String s:f1)
			{
				categories1.add(s);
			}
			
			System.out.println(categories1.toString());
			return categories1;
			
		}
		
		@RequestMapping("/categories/{c}")
		public List<Product> productByCategory(@PathVariable String c)
		{
			List<Product> p1=productService.ListProducts().stream().filter(n->n.getProductCategory().equals(c)).collect(Collectors.toList());
			return p1;
		}
		
		@RequestMapping("/productsName")
		public List<String> getName()
		{
			List<String> names=new ArrayList<>();
			List<Product> p1=productService.ListProducts();
			
			for(Product p:p1)
			{
				names.add(p.getProductName());
			}
			
			System.out.println(names.toString());
			return names;
		}

		@RequestMapping("test")
		public Product addProduct()
		{
			Product p = new Product("Product1", "./assets/image3.jpg", 500, 4, 2, "B1", 100, "Wired Earphones", 2,
					"Mobile Accessories", true, true, true);
			Product p1=productService.addProduct(p);
			return p1;
		}		
	}


