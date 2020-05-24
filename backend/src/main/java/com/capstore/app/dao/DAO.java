package com.capstore.app.dao;

import java.util.List;

import com.capstore.app.models.CommonFeedback;
import com.capstore.app.models.CommonFeedback1;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.LocalCart;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.ProductFeedback1;

public interface DAO {
	public boolean createFeedback(ProductFeedback pf);
	public ProductFeedback getFeedbackByProductId(int productId);

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(Product product);

	public Product getProductById(int productId);
	
	public List<Product> ListProducts();
	public Product ListProductsByName(String name);

	int ListProductIdByName(String name);
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
	CustomerDetails getCustomerDetailById(Integer id);
	CustomerDetails updateCustomerDetails(CustomerDetails custDetails);
	CustomerDetails createCommonFeedback(CommonFeedback1 commonFeedback);
	List<String> getOrderedProductName(int id);
	String getNameByProductId(int id);
}
