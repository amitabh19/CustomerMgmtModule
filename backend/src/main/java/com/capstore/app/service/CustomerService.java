package com.capstore.app.service;

import java.util.List;

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

public interface CustomerService {
	public CustomerDetails getCustomerDetailsById(Integer id) throws ResourceNotFoundException;

	public CustomerDetails updateCustomerDetails(CustomerDetails customer);

	Product addProduct(Product product);

	Product updateProduct(Product product);

	void deleteProduct(Product product);

	Product getProductById(int productId) throws ResourceNotFoundException;

	List<Product> ListProducts();

	public List<String> categories();
	
	public List<Product> productByCategory(String str);

	List<CustomerDetails> getAllCustomers();

	CustomerDetails getCustomerById(int id);

	List<MerchantDetails> getAllMerchants();

	MerchantDetails getMerchantById(int id);

	List<Product> getAllProducts();

	List<ProductFeedback> getAllProductFeedbacks();

	ProductFeedback getProductFeedbackById(int id);

	CommonFeedback getCommonFeedbackById(int id);

	List<CommonFeedback> getAllCommonFeedbacks();

	List<Order> getAllOrders();

	Order getOrderById(int id);

	CustomerDetails addToCartBC(LocalCart lc);

	List<Product> cartProducts(int id);

	List<Product> wishProducts(int id);

	CustomerDetails addToWishlist(LocalCart lc);

	String addToCartFromWishList(int id);

	String deleteFromCart(int id);

	CustomerDetails addToCartPut(CustomerDetails c);

	CustomerDetails create(ProductFeedback1 productFeedback);

	CustomerDetails getCustomerDetailById(Integer id) throws ResourceNotFoundException;

	CustomerDetails createCommonFeedback(CommonFeedback1 commonFeedback);

	List<String> getOrderedProductName(int id);

	String getNameByProductId(int id);

}
