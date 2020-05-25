package com.capstore.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstore.app.dao.DAO;
import com.capstore.app.dao.DAOImpl;
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
	public Product getProductById(int productId) {
		logger.trace("getProductById is accessed  at Service layer");
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> ListProducts() {
		logger.trace("ListProduct is accessed  at Service layer");
		return productDao.ListProducts();
	}

	@Override
	public Product ListProductsByName(String name) {
		logger.trace("ListProductsByName is accessed  at Service layer");
		return productDao.ListProductsByName(name);
	}

	@Override
	public int ListProductIdByName(String name) {
		logger.trace("ListProductIdByName is accessed  at Service layer");
		return productDao.ListProductIdByName(name);
	}

	@Override
	public CustomerDetails getCustomerDetailsById(Integer id) {
		logger.trace("getCustomerDetailsById is accessed  at Service layer");
		return dao.getCustomerDetailById(id);

	}

	@Override
	public boolean createFeedback(ProductFeedback pf) {
		logger.trace("createFeedback is accessed  at Service layer");
		return dao.createFeedback(pf);
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

	@Override
	public List<ProductFeedback> getAllProductFeedbacks() {
		logger.trace("getAllProductFeedbacks is accessed  at Service layer");
		return dao.getAllProductFeedbacks();
	}

	@Override
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		logger.trace("getProductFeedbackById is accessed  at Service layer");
		return dao.getProductFeedbackById(id);
	}

	@Override
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		logger.trace("getCommonFeedbackById is accessed  at Service layer");
		return dao.getCommonFeedbackById(id);
	}

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

	// function for product feedback
	@Override
	public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
		// System.out.println("The product feedback object is: " +
		// productFeedback.toString());
		logger.trace("create product feedback is accessed  at Service layer");
		return dao.create(productFeedback);
	}

	// get customer by id
	@Override
	public CustomerDetails getCustomerDetailById(@PathVariable Integer id) {
		logger.trace("getCustomerDetailById is accessed  at Service layer");
		return dao.getCustomerDetailById(id);
	}

	// Nikhil
	@Override
	public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails) {
		logger.trace("updateCustomerDetails is accessed  at Service layer");
		return dao.updateCustomerDetails(custDetails);
	}

	// function for common feedback
	@Override
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {

		logger.trace("createCommonFeedback is accessed  at Service layer");
		return dao.createCommonFeedback(commonFeedback);

	}

	@Override
	public List<String> getOrderedProductName(@PathVariable int id) {
		logger.trace("getOrderedProductName is accessed  at Service layer");
		return dao.getOrderedProductName(id);
	}

	@Override
	public String getNameByProductId(@PathVariable int id) {
		logger.trace("getNameByProductId is accessed  at Service layer");
		return dao.getNameByProductId(id);
	}

}
