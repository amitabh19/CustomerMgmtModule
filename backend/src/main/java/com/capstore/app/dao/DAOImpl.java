package com.capstore.app.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


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

@Repository
public class DAOImpl implements DAO {

	@Autowired
	EntityManager em;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomerRepository customerRepository;

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

	Logger logger = LoggerFactory.getLogger(DAOImpl.class);

	/*
	 * This function is used to add products to the database
	 * @return productRepository.save(product) would save the product and returns the product object
	 * */
	@Override
	public Product addProduct(Product product) {
		logger.trace("Add Product is accessed  at DAO layer");
		return productRepository.save(product);

	}

	/*
	 * This function is used to update product details
	 * @return productRepository.save(product) would save the updated version of the 
	 * product and would return the object saved in database
	 * */
	@Override
	public Product updateProduct(Product product) {
		logger.trace("Update Product is accessed  at DAO layer");
		return productRepository.save(product);
	}

	
	/*
	 * This function is used to delete any product from database 
	 * */
	@Override
	public void deleteProduct(Product product) {
		logger.trace("Delete Product is accessed  at DAO layer");
		productRepository.deleteById(product.getProductId());
	}

	/*
	 * This function returns the list of all products so that they can be used to create the frontend
	 * */
	@Override
	public List<Product> ListProducts() {
		logger.trace("ListProducts is accessed  at DAO layer");
		return productRepository.findAll();

	}
	
	@Override
	public List<String> categories()
	{
		List<Product> p = productRepository.findAll();
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
	
	@Override
	public List<Product> productByCategory(String str)
	{
		List<Product> p1 = productRepository.findAll().stream().filter(n -> n.getProductCategory().equals(str))
				.collect(Collectors.toList());
		return p1;
	}

	@Override
	public List<CustomerDetails> getAllCustomers() {
		logger.trace("getAllCustomers is accessed  at DAO layer");
		return customerRepository.findAll();
	}

	@Override
	public CustomerDetails getCustomerById(@PathVariable int id) {
		logger.trace("getCustomerById is accessed  at DAO layer");
		return customerRepository.getOne(id);
	}

	@Override
	public List<MerchantDetails> getAllMerchants() {
		logger.trace("getAllMerchants is accessed  at DAO layer");
		return merchantRepository.findAll();
	}

	@Override
	public MerchantDetails getMerchantById(@PathVariable int id) {
		logger.trace("getMerchantById is accessed  at DAO layer");
		return merchantRepository.getOne(id);
	}

	@Override
	public List<Product> getAllProducts() {
		logger.trace("getAllProducts is accessed  at DAO layer");
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(@PathVariable int id) {
		logger.trace("getProductById is accessed  at DAO layer");
		return productRepository.findById(id);
	}

	/**
	 * This method is used to get all the Product Feedbacks
	 * 
	 * @return List<ProductFeedback> This returns the list of all product feedback
	 *         generated
	 */
	@Override
	public List<ProductFeedback> getAllProductFeedbacks() {
		logger.trace("getAllProductFeedbacks is accessed  at DAO layer");
		return productFeedbackRepository.findAll();
	}

	/**
	 * This method is used to get Product Feedback by using id
	 * 
	 * @param id This is the parameter to assign feedback id
	 * @return ProductFeedback This returns the object of ProductFeedback generated
	 */
	@Override
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		logger.trace("getProductFeedbackById is accessed  at DAO layer");
		return productFeedbackRepository.getOne(id);
	}

	/**
	 * This method is used to get Common Feedback by using id
	 * 
	 * @param id This is the parameter to assign feedback id
	 * @return CommonFeedback This returns the object of CommonFeedback generated
	 */
	@Override
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		logger.trace("getCommonFeedbackById is accessed  at DAO layer");
		return commonFeedbackRepository.getOne(id);
	}

	/**
	 * This method is used to get all the Common Feedbacks
	 * 
	 * @return List<CommonFeedback> This returns the list of all common feedback
	 *         generated
	 */
	@Override
	public List<CommonFeedback> getAllCommonFeedbacks() {
		logger.trace("getAllCommonFeedbacks is accessed  at DAO layer");
		return commonFeedbackRepository.findAll();
	}

	@Override
	public List<Order> getAllOrders() {
		logger.trace("getAllOrders is accessed  at DAO layer");
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(@PathVariable int id) {
		logger.trace("getOrderById is accessed  at DAO layer");
		return orderRepository.getOne(id);
	}

	// function to get numbers from json
	public int stringCleaner(String obj) {
		logger.trace("stringCleaner is accessed  at DAO layer");
		String[] arr1 = obj.split("=");
		String q = arr1[1];
		int quan = Integer.parseInt(q);
		return quan;
	}

	// post function to add to cart
	@Override
	public CustomerDetails addToCartBC(@RequestBody LocalCart lc) {
		logger.trace("addToCartBC is accessed  at DAO layer");
		// LocalCart : [uid =1, pid=2, quan=3]

		int quan = lc.getQuantity();
		int cid = lc.getCid();
		CustomerDetails c = customerRepository.getOne(cid);
		Product p = lc.getPid();
		Set<Cart> cart = c.getCustomerCarts();

		for (Cart t : cart) {
			System.out.println("The type of cart is:" + t.getType());
			if (t.getProductId() == p.getProductId() && t.getType().equals("cart")) {
				t.setQuantity(t.getQuantity() + quan);
				if(t.getQuantity()>5) {
					int q = 5;
					t.setQuantity(q);
				}
				customerRepository.save(c);
				return customerRepository.save(c);
			}
		}

		Cart ct = new Cart("cart", quan, p.getProductId());
		cart.add(ct);
		c.setCustomerCarts(cart);
		return customerRepository.save(c);

	}

	@Override
	public List<Product> cartProducts(@PathVariable int id) {
		logger.trace("cartProducts is accessed  at DAO layer");
		CustomerDetails c = customerRepository.getOne(id);
		List<Product> lp = new ArrayList<>();
		Set<Cart> cc = c.getCustomerCarts();
		for (Cart cp : cc) {
			if (cp.getType().equals("cart")) {
				lp.add(productRepository.getOne(cp.getProductId()));
			}
		}
		return lp;

	}

	@Override
	public List<Product> wishProducts(@PathVariable int id) {
		logger.trace("wishProducts is accessed  at DAO layer");
		CustomerDetails c = customerRepository.getOne(id);
		List<Product> lp = new ArrayList<>();
		Set<Cart> cc = c.getCustomerCarts();
		for (Cart cp : cc) {
			if (cp.getType().equals("wishlist")) {
				lp.add(productRepository.getOne(cp.getProductId()));
			}
		}
		return lp;

	}

	// post function to add to wishlist
	@Override
	public CustomerDetails addToWishlist(@RequestBody LocalCart lc) {
		logger.trace("addToWishlist is accessed  at DAO layer");

		int quan = lc.getQuantity();
		int pid = lc.getPid().getProductId();
		int cid = lc.getCid();
		CustomerDetails c = customerRepository.getOne(cid);
		Product p = productRepository.getOne(pid);
		Set<Cart> cart = c.getCustomerCarts();

		for (Cart t : cart) {
			if (t.getProductId() == p.getProductId() && t.getType().equals("wishlist")) {
				t.setQuantity(t.getQuantity() + quan);
				if(t.getQuantity()>5) {
					int q = 5;
					t.setQuantity(q);
				}
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

	// function to delete wishlist
	@Override
	public String addToCartFromWishList(@PathVariable int id) {
		logger.trace("addToCartFromWishList is accessed  at DAO layer");
		cartRepository.deleteById(id);

		return "deleted from wishlist";
	}

	// function to delete from cart
	@Override
	public String deleteFromCart(@PathVariable int id) {
		logger.trace("deleteFromCart is accessed  at DAO layer");
		cartRepository.deleteById(id);
		return "deleted";
	}

	// put function to add to cart
	@Override
	public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		logger.trace("addToCartPut is accessed  at DAO layer");
		return customerRepository.save(c);
	}
	
	// get function to get customer details
	@Override
	public Optional<CustomerDetails> getCustomerDetailById(@PathVariable Integer id) {
		logger.trace("getCustomerDetailById is accessed  at DAO layer");
		//return customerRepository.getOne(id);
		return customerRepository.findById(id);
	}

	// function to update customer details
	@Override
	public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails) {
		logger.trace("updateCustomerDetails is accessed  at DAO layer");
		return customerRepository.save(custDetails);
	}

	/**
	This method is used to create new Product Feedback
	@param ProductFeedback1 This is the parameter to assign ProductFeedback1 object 
	@return CustomerDetails This returns the object of CustomerDetails generated
	*/
	@Override
	public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
		logger.trace("create product feedback is accessed  at DAO layer");
		int custId = productFeedback.getUserId();
		String feedSubject = productFeedback.getFeedbackSubject();
		String feedMessage = productFeedback.getFeedbackMessage();
		int productId = productFeedback.getProductId();

		ProductFeedback pf = new ProductFeedback(feedSubject, feedMessage, productId);
		Set<ProductFeedback> pfset = new HashSet<ProductFeedback>();
		pfset.add(pf);
		logger.info("feedback added to ProductFeedback Set");

		CustomerDetails c = customerRepository.getOne(custId);
		Set<ProductFeedback> res = c.getProductFeedbacks();
		res.add(pf);
		c.setProductFeedbacks(res);
		logger.info("feedback added to ProductFeedback Set of respective Customer");

		return customerRepository.save(c);
	}

	/**
	This method is used to create new Common Feedback
	@param CommonFeedback1 This is the parameter to assign CommonFeedback1 object 
	@return CustomerDetails This returns the object of CustomerDetails generated
	*/
	@Override
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {
		logger.trace("createCommonFeedback is accessed  at DAO layer");

		int custId = commonFeedback.getUserId();
		String feedSubject = commonFeedback.getFeedbackSubject();
		String feedMessage = commonFeedback.getFeedbackMessage();
		int productId = commonFeedback.getProductId();

		boolean requestFlag = true;
		boolean responseFlag = false;
		boolean requestApproved = false;
		boolean responseApproved = false;
		String responseMessage = null;

		CommonFeedback cf = new CommonFeedback(feedSubject, feedMessage, productId, requestFlag, responseFlag,
				requestApproved, responseApproved, responseMessage);
		Set<CommonFeedback> cfset = new HashSet<CommonFeedback>();
		cfset.add(cf);
		logger.info("feedback added to CommonFeedback Set");
		
		CustomerDetails c = customerRepository.getOne(custId);
		Set<CommonFeedback> res = c.getFeedbacks();
		res.add(cf);
		c.setFeedbacks(res);
		logger.info("feedback added to CommonFeedback Set of respective Customer");
		
		return customerRepository.save(c);

	}
	
	/**
	This method is used to get the name of product ordered by the customer by using customer id
	@param int This is the parameter to assign customer user_id 
	@return List<String> This returns the list of product name ordered by customer generated after calling DAO
	*/
	@Override
	public List<String> getOrderedProductName(@PathVariable int id) {
		logger.trace("getOrderedProductName is accessed  at DAO layer");
		CustomerDetails cust = customerRepository.getOne(id);
		Set<Order> orders = cust.getOrders();
		Set<Integer> prodList = new HashSet<Integer>();
		List<String> orderedProductName = new ArrayList<String>();
		for (Order order : orders) {
			Map<Integer, Integer> products = order.getProducts();
			prodList.addAll(products.values());
		}
		for (Integer prod : prodList) {
			orderedProductName.add(getNameByProductId(prod));
		}
		return orderedProductName;
	}
	
	/**
	This method is used to get the name of product by using product id
	@param int This is the parameter to assign product id 
	@return String This returns the product name generated after calling DAO
	*/
	@Override
	public String getNameByProductId(@PathVariable int id) {
		logger.trace("getNameByProductId is accessed  at DAO layer");
		return productRepository.getOne(id).getProductName();
	}

	@Override
	public int ListProductIdByName(String name) {
		logger.trace("ListProductIdByName is accessed  at DAO layer");
		List<Product> list1 = productRepository.findAll();
		List<Product> list2 = list1.stream().filter(n -> n.getProductName().equals(name)).collect(Collectors.toList());
		int pId = list2.get(0).getProductId();
		return pId;
	}

}
