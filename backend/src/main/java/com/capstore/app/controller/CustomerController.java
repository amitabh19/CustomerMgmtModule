package com.capstore.app.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.app.models.Cart;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Product;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.ProductRepository;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.service.ServiceImpl;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ServiceImpl serv;

	// random comment
	// krapiya yha apne functions bana dijiye
	// mike testing

	@PostMapping("/atC")
	public CustomerDetails addToCart(@RequestBody CustomerDetails c, @RequestBody Product p) {
		Set<Cart> cart = c.getCustomerCarts();
		Cart ct = new Cart("cart", 1, p.getProductId());
		cart.add(ct);
		c.setCustomerCarts(cart);
		customerRepository.save(c);
		return c;

	}

	@GetMapping("/atC/{cid}/{prodid}/{quan}")
	public CustomerDetails addToCartGet(@PathVariable int cid, @PathVariable int prodid, @PathVariable int quan) {
		CustomerDetails c = customerRepository.getOne(cid);
		Product p = productRepository.getOne(prodid);
		Set<Cart> cart = c.getCustomerCarts();

		for (Cart t : cart) {
			if (t.getProductId() == p.getProductId()) {
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

	@RequestMapping("/customers")
	public List<CustomerDetails> getAllCustomers() {
		return customerRepository.findAll();
	}

	@RequestMapping("customer/{id}")
	public CustomerDetails getCustomerById(@PathVariable int id) {
		return customerRepository.getOne(id);
	}

	@RequestMapping("/merchants")
	public List<MerchantDetails> getAllMerchants() {
		return merchantRepository.findAll();
	}

	@RequestMapping("/merchant/{id}")
	public MerchantDetails getMerchantById(@PathVariable int id) {
		return merchantRepository.getOne(id);
	}

	@RequestMapping("/products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@RequestMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return productRepository.getOne(id);
	}

	@RequestMapping("/merchprod/{id}")
	public MerchantDetails getMerchantofProductById(@PathVariable int id) {
		return productRepository.getOne(id).getMd();
	}

	@PostMapping("/addFeedback")
	public boolean create(@RequestBody ProductFeedback pf) {
		return serv.createFeedback(pf);
	}

	@GetMapping("/getProductFeedback/{productId}")
	public ProductFeedback getFeedbackByProductId(@PathVariable int productId) {
		return serv.getFeedbackByProductId(productId);
	}
}
