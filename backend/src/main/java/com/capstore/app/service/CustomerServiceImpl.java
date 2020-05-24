package com.capstore.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstore.app.dao.DAO;
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
import com.capstore.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private DAO productDao;
	
	@Autowired
	private DAO dao;

	@Override
	public Product addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
		
	}

	@Override
	public Product getProductById(int productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> ListProducts() {
		return productDao.ListProducts();
	}

	@Override
	public Product ListProductsByName(String name) {
		return productDao.ListProductsByName(name);
	}

	@Override
	public int ListProductIdByName(String name) {
		return productDao.ListProductIdByName(name);
	}
	
	
	@Override
	public CustomerDetails getCustomerDetailsById(Integer id) {
		
		return dao.getCustomerDetailById(id);
		
	}

	
	@Override
	public boolean createFeedback(ProductFeedback pf) {
	
		return dao.createFeedback(pf);
	}

	@Override
	public ProductFeedback getFeedbackByProductId(int productId) {
		
		return dao.getFeedbackByProductId(productId);
	}
	
	
	@Override
	public List<CustomerDetails> getAllCustomers() {
		return dao.getAllCustomers();
	}

	@Override
	public CustomerDetails getCustomerById(@PathVariable int id) {
		return dao.getCustomerById(id);
	}

	@Override
	public List<MerchantDetails> getAllMerchants() {
		return dao.getAllMerchants();
	}

	@Override
	public MerchantDetails getMerchantById(@PathVariable int id) {
		return dao.getMerchantById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}


	@Override
	public List<ProductFeedback> getAllProductFeedbacks() {
		return dao.getAllProductFeedbacks();
	}

	@Override
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		return dao.getProductFeedbackById(id);
	}

	@Override
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		return dao.getCommonFeedbackById(id);
	}

	@Override
	public List<CommonFeedback> getAllCommonFeedbacks() {
		return dao.getAllCommonFeedbacks();
	}

	@Override
	public List<Order> getAllOrders() {
		return dao.getAllOrders();
	}

	@Override
	public Order getOrderById(@PathVariable int id) {
		return dao.getOrderById(id);
	}

	// function to get numbers from json
	public int stringCleaner(String obj) {
		String[] arr1 = obj.split("=");
		String q = arr1[1];
		int quan = Integer.parseInt(q);
		return quan;
	}

	// post function to add to cart
	@Override
	public CustomerDetails addToCartBC(@RequestBody LocalCart lc) {
		// LocalCart : [uid =1, pid=2, quan=3]

		return dao.addToCartBC(lc);

	}

	@Override
	public List<Product> cartProducts(@PathVariable int id) {
		
		return dao.cartProducts(id);

	}
	
	@Override
	public List<Product> wishProducts(@PathVariable int id) {
	
		return dao.wishProducts(id);

	}

	// post function to add to wishlist
	@Override
	public CustomerDetails addToWishlist(@RequestBody LocalCart lc) {

		return dao.addToWishlist(lc);

	}

	// function to delete wishlist
	@Override
	public String addToCartFromWishList(@PathVariable int id) {
		
		return dao.addToCartFromWishList(id);
	}

	// function to delete from cart
	@Override
	public String deleteFromCart(@PathVariable int id) {
		
		return dao.deleteFromCart(id);
	}

	// put function to add to cart
	@Override
	public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		return dao.addToCartPut(c);

	}
		
	// function for product feedback
	@Override
		public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
			//System.out.println("The product feedback object is: " + productFeedback.toString());
			
			return dao.create(productFeedback);
		}

		
	//get customer by id
	@Override
		public CustomerDetails getCustomerDetailById(@PathVariable Integer id)
		{
			return dao.getCustomerDetailById(id);
		}
		
		//Nikhil
	@Override
		public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails)
		{
			return dao.updateCustomerDetails(custDetails);
		}
		
	
		
	//function for common feedback
	@Override
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {
		

		return dao.createCommonFeedback(commonFeedback);

	}



	
	@Override
	public List<String> getOrderedProductName(@PathVariable int id) {
		
		return dao.getOrderedProductName(id);
	}
	
	@Override
	public String getNameByProductId(@PathVariable int id) {
		return dao.getNameByProductId(id);
	}
	
	
	
}
