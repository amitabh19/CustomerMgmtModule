package com.capstore.app.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	Logger logger=LoggerFactory.getLogger(DAOImpl.class);

	
	@Override
	public boolean createFeedback(ProductFeedback pf) {
		logger.trace("Create Feedback is accessed  at DAO layer");
		em.persist(pf);
		return true;
	}
	
	@Override
	public Product addProduct(Product product) {
		logger.trace("Add Product is accessed  at DAO layer");
		return productRepository.save(product);
		
	}

	@Override
	public Product updateProduct(Product product) {
		logger.trace("Update Product is accessed  at DAO layer");
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		logger.trace("Delete Product is accessed  at DAO layer");
		productRepository.deleteById(product.getProductId());
	}


	@Override
	public Product ListProductsByName(String name) {
		logger.trace("ListProductsByName is accessed  at DAO layer");
		List<Product> list1=productRepository.findAll();
		List<Product> list2=list1.stream().filter(n->n.getProductName().equals(name)).collect(Collectors.toList());
		Product p=list2.get(0);
		return p;
	}
	
	@Override
	public int ListProductIdByName(String name) {
		logger.trace("ListProductIdByName is accessed  at DAO layer");
		List<Product> list1=productRepository.findAll();
		List<Product> list2=list1.stream().filter(n->n.getProductName().equals(name)).collect(Collectors.toList());
		int pId=list2.get(0).getProductId();
		return pId;
	}

	@Override
	public List<Product> ListProducts() {
		logger.trace("ListProducts is accessed  at DAO layer");
		return productRepository.findAll();
		
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
	public Product getProductById(@PathVariable int id) {
		logger.trace("getProductById is accessed  at DAO layer");
		return productRepository.getOne(id);
	}

	@Override
	public List<ProductFeedback> getAllProductFeedbacks() {
		logger.trace("getAllProductFeedbacks is accessed  at DAO layer");
		return productFeedbackRepository.findAll();
	}

	@Override
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		logger.trace("getProductFeedbackById is accessed  at DAO layer");
		return productFeedbackRepository.getOne(id);
	}

	@Override
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		logger.trace("getCommonFeedbackById is accessed  at DAO layer");
		return commonFeedbackRepository.getOne(id);
	}

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
		
		// function for product feedback
	@Override
		public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
		logger.trace("create product feedback is accessed  at DAO layer");
			//System.out.println("The product feedback object is: " + productFeedback.toString());
			int custId=productFeedback.userId;
			String feedSubject=productFeedback.feedbackSubject;
			String feedMessage=productFeedback.feedbackMessage;
			int productId=productFeedback.productId;

			ProductFeedback pf = new ProductFeedback(feedSubject, feedMessage, productId);
			Set<ProductFeedback> pfset = new HashSet<ProductFeedback>();
			pfset.add(pf);

			System.out.println(pfset);

			CustomerDetails c = customerRepository.getOne(custId);
			Set<ProductFeedback> res = c.getProductFeedbacks();
			res.add(pf);
			c.setProductFeedbacks(res);
			System.out.println(res);

			return customerRepository.save(c);
		}

		
		//Nikhil
	@Override
		public CustomerDetails getCustomerDetailById(@PathVariable Integer id)
		{
		logger.trace("getCustomerDetailById is accessed  at DAO layer");
			return customerRepository.getOne(id);
		}
		
		//Nikhil
	@Override
		public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails)
		{
		logger.trace("updateCustomerDetails is accessed  at DAO layer");
			return customerRepository.save(custDetails);
		}
		
	
		
	//function for common feedback
	@Override
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {
		logger.trace("createCommonFeedback is accessed  at DAO layer");
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

		return customerRepository.save(c);

	}



	
	@Override
	public List<String> getOrderedProductName(@PathVariable int id) {
		logger.trace("getOrderedProductName is accessed  at DAO layer");
		CustomerDetails cust=customerRepository.getOne(id);
		Set<Order> orders=cust.getOrders();
		Set<Integer> prodList= new HashSet<Integer>();
		List<String> orderedProductName=new ArrayList<String>();
		for (Order order : orders) {
			Map<Integer,Integer> products=order.getProducts();
			prodList.addAll(products.values());
		}
		for(Integer prod: prodList) {
			orderedProductName.add(getNameByProductId(prod));
		}
		return orderedProductName;
	}
	
	@Override
	public String getNameByProductId(@PathVariable int id) {
		logger.trace("getNameByProductId is accessed  at DAO layer");
		return productRepository.getOne(id).getProductName();
	}
	
	

	

	

}



