package com.capstore.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstore.app.dao.DAO;
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

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	private DAO productDao;
	@Autowired
	private DAO dao;

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Product addProduct(Product product) {
		logger.trace("Add Product is accessed  at Service layer");
		return productDao.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		logger.trace("Update Product is accessed  at Service layer");
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		logger.trace("Delete Product is accessed  at Service layer");
		productDao.deleteProduct(product);

	}

	@Override
	public Product getProductById(int productId) throws ResourceNotFoundException {
		logger.trace("getProductById is accessed  at Service layer");
		Optional<Product> p= productDao.getProductById(productId);
		if(p.isPresent())
		{
			return p.get();
		}
		else {
			throw new ResourceNotFoundException("No product record exist for given id");
		}
		//return productDao.getProductById(productId);
	}

	@Override
	public List<Product> ListProducts() {
		logger.trace("ListProduct is accessed  at Service layer");
		return productDao.ListProducts();
	}

	@Override
    public List<String> categories()
    {
		return productDao.categories();
    }
	
	public List<Product> productByCategory(String str)
	{
		return productDao.productByCategory(str);
	}

	@Override
	public CustomerDetails getCustomerDetailsById(Integer id) throws ResourceNotFoundException {
		logger.trace("getCustomerDetailsById is accessed  at Service layer");
		Optional<CustomerDetails> cd =dao.getCustomerDetailById(id);
		if(cd.isPresent()) {
			return cd.get();
		}
		else {
			throw new ResourceNotFoundException("No customer record exist for given id");
		}
		//return dao.getCustomerDetailById(id);

	}

	@Override
	public List<CustomerDetails> getAllCustomers() {
		logger.trace("getAllCustomers is accessed  at Service layer");
		return dao.getAllCustomers();
	}

	@Override
	public CustomerDetails getCustomerById(@PathVariable int id) {
		logger.trace("getCustomerById is accessed  at Service layer");
		return dao.getCustomerById(id);
	}

	@Override
	public List<MerchantDetails> getAllMerchants() {
		logger.trace("getAllMerchants is accessed  at Service layer");
		return dao.getAllMerchants();
	}

	@Override
	public MerchantDetails getMerchantById(@PathVariable int id) {
		logger.trace("getMerchantById is accessed  at Service layer");
		return dao.getMerchantById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		logger.trace("getAllProducts is accessed  at Service layer");
		return dao.getAllProducts();
	}
	
	/**
	This method is used to get all the Product Feedbacks
	@return List<ProductFeedback> This returns the list of all product feedback generated after calling DAO
	*/
	@Override
	public List<ProductFeedback> getAllProductFeedbacks() {
		logger.trace("getAllProductFeedbacks is accessed  at Service layer");
		return dao.getAllProductFeedbacks();
	}
	
	/**
	This method is used to get Product Feedback by using id 
	@param id This is the parameter to assign feedback id 
	@return ProductFeedback This returns the object of ProductFeedback generated after calling DAO
	*/
	@Override
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		logger.trace("getProductFeedbackById is accessed  at Service layer");
		return dao.getProductFeedbackById(id);
	}
	
	/**
	This method is used to get Common Feedback by using id 
	@param id This is the parameter to assign feedback id 
	@return CommonFeedback This returns the object of CommonFeedback generated after calling DAO
	*/
	@Override
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		logger.trace("getCommonFeedbackById is accessed  at Service layer");
		return dao.getCommonFeedbackById(id);
	}
	
	/**
	This method is used to get all the Common Feedbacks
	@return List<CommonFeedback> This returns the list of all common feedback generated after calling DAO
	*/
	@Override
	public List<CommonFeedback> getAllCommonFeedbacks() {
		logger.trace("getAllCommonFeedbacks is accessed  at Service layer");
		return dao.getAllCommonFeedbacks();
	}

	@Override
	public List<Order> getAllOrders() {
		logger.trace("getAllOrders is accessed  at Service layer");
		return dao.getAllOrders();
	}

	@Override
	public Order getOrderById(@PathVariable int id) {
		logger.trace("getOrderById is accessed  at Service layer");
		return dao.getOrderById(id);
	}

	// function to get numbers from json
	public int stringCleaner(String obj) {
		logger.trace("stringCleaner is accessed  at Service layer");
		String[] arr1 = obj.split("=");
		String q = arr1[1];
		int quan = Integer.parseInt(q);
		return quan;
	}

	// post function to add to cart
	@Override
	public CustomerDetails addToCartBC(@RequestBody LocalCart lc) {
		// LocalCart : [uid =1, pid=2, quan=3]
		logger.trace("addToCartBC is accessed  at Service layer");
		return dao.addToCartBC(lc);

	}

	@Override
	public List<Product> cartProducts(@PathVariable int id) {
		logger.trace("cartProducts is accessed  at Service layer");
		return dao.cartProducts(id);

	}

	@Override
	public List<Product> wishProducts(@PathVariable int id) {
		logger.trace("wishProducts is accessed  at Service layer");
		return dao.wishProducts(id);

	}

	// post function to add to wishlist
	@Override
	public CustomerDetails addToWishlist(@RequestBody LocalCart lc) {
		logger.trace("addToWishlist is accessed  at Service layer");
		return dao.addToWishlist(lc);

	}

	// function to delete wishlist
	@Override
	public String addToCartFromWishList(@PathVariable int id) {
		logger.trace("addToCartFromWishList is accessed  at Service layer");
		return dao.addToCartFromWishList(id);
	}

	// function to delete from cart
	@Override
	public String deleteFromCart(@PathVariable int id) {
		logger.trace("deleteFromCart is accessed  at Service layer");
		return dao.deleteFromCart(id);
	}

	// put function to add to cart
	@Override
	public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		logger.trace("addToCartPut is accessed  at Service layer");
		return dao.addToCartPut(c);

	}
	
	// get customer by id
		@Override
		public CustomerDetails getCustomerDetailById(@PathVariable Integer id) throws ResourceNotFoundException {
			logger.trace("getCustomerDetailById is accessed  at Service layer");
			//return dao.getCustomerDetailById(id);
			Optional<CustomerDetails> cd =dao.getCustomerDetailById(id);
			if(cd.isPresent()) {
				return cd.get();
			}
			else {
				throw new ResourceNotFoundException("No customer record exist for given id");
			}
		}

		// Nikhil
		@Override
		public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails) {
			logger.trace("updateCustomerDetails is accessed  at Service layer");
			return dao.updateCustomerDetails(custDetails);
		}

	/**
	This method is used to create new Product Feedback
	@param ProductFeedback1 This is the parameter to assign ProductFeedback1 object 
	@return CustomerDetails This returns the object of CustomerDetails generated after calling DAO
	*/
	@Override
	public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
		logger.trace("create product feedback is accessed  at Service layer");
		return dao.create(productFeedback);
	}
	
	/**
	This method is used to create new Common Feedback
	@param CommonFeedback1 This is the parameter to assign CommonFeedback1 object 
	@return CustomerDetails This returns the object of CustomerDetails generated after calling DAO
	*/
	@Override
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {

		logger.trace("createCommonFeedback is accessed  at Service layer");
		return dao.createCommonFeedback(commonFeedback);

	}
	
	/**
	This method is used to get the name of product ordered by the customer by using customer id
	@param int This is the parameter to assign customer user_id 
	@return List<String> This returns the list of product name ordered by customer generated after calling DAO
	*/
	@Override
	public List<String> getOrderedProductName(@PathVariable int id) {
		logger.trace("getOrderedProductName is accessed  at Service layer");
		return dao.getOrderedProductName(id);
	}
	
	/**
	This method is used to get the name of product by using product id
	@param int This is the parameter to assign product id 
	@return String This returns the product name generated after calling DAO
	*/
	@Override
	public String getNameByProductId(@PathVariable int id) {
		logger.trace("getNameByProductId is accessed  at Service layer");
		return dao.getNameByProductId(id);
	}

	@Override
	public int ListProductIdByName(String name) {
		logger.trace("ListProductIdByName is accessed  at Service layer");
		return productDao.ListProductIdByName(name);
	}

}
