package com.capstore.app.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	
	@Override
	public boolean createFeedback(ProductFeedback pf) {
		em.persist(pf);
		return true;
	}

	@Override
	public ProductFeedback getFeedbackByProductId(int productId) {
		try {
			//ProductFeedback pf = em.find(ProductFeedback.class, product_id);
			String query1 = "select p from ProductFeedback p where p.productId=:productId";
			TypedQuery<ProductFeedback> q1 = em.createQuery(query1, ProductFeedback.class);
			q1.setParameter("productId", productId);
			ProductFeedback pf = q1.getSingleResult();
			//System.out.println(pf);
			return pf;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
		
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.deleteById(product.getProductId());
	}


	@Override
	public Product ListProductsByName(String name) {
		List<Product> list1=productRepository.findAll();
		List<Product> list2=list1.stream().filter(n->n.getProductName().equals(name)).collect(Collectors.toList());
		Product p=list2.get(0);
		return p;
	}
	
	@Override
	public int ListProductIdByName(String name) {
		List<Product> list1=productRepository.findAll();
		List<Product> list2=list1.stream().filter(n->n.getProductName().equals(name)).collect(Collectors.toList());
		int pId=list2.get(0).getProductId();
		return pId;
	}

	@Override
	public List<Product> ListProducts() {
		return productRepository.findAll();
		
	}
	
	@Override
	public List<CustomerDetails> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public CustomerDetails getCustomerById(@PathVariable int id) {
		return customerRepository.getOne(id);
	}

	@Override
	public List<MerchantDetails> getAllMerchants() {
		return merchantRepository.findAll();
	}

	@Override
	public MerchantDetails getMerchantById(@PathVariable int id) {
		return merchantRepository.getOne(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(@PathVariable int id) {
		return productRepository.getOne(id);
	}

	@Override
	public List<ProductFeedback> getAllProductFeedbacks() {
		return productFeedbackRepository.findAll();
	}

	@Override
	public ProductFeedback getProductFeedbackById(@PathVariable int id) {
		return productFeedbackRepository.getOne(id);
	}

	@Override
	public CommonFeedback getCommonFeedbackById(@PathVariable int id) {
		return commonFeedbackRepository.getOne(id);
	}

	@Override
	public List<CommonFeedback> getAllCommonFeedbacks() {
		return commonFeedbackRepository.findAll();
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(@PathVariable int id) {
		return orderRepository.getOne(id);
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
		cartRepository.deleteById(id);
		
		return "deleted from wishlist";
	}

	// function to delete from cart
	@Override
	public String deleteFromCart(@PathVariable int id) {
		cartRepository.deleteById(id);
		return "deleted";
	}

	// put function to add to cart
	@Override
	public CustomerDetails addToCartPut(@RequestBody CustomerDetails c) {
		return customerRepository.save(c);

	}
		
		// function for product feedback
	@Override
		public CustomerDetails create(@RequestBody ProductFeedback1 productFeedback) {
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
			return customerRepository.getOne(id);
		}
		
		//Nikhil
	@Override
		public CustomerDetails updateCustomerDetails(@RequestBody CustomerDetails custDetails)
		{
			return customerRepository.save(custDetails);
		}
		
	
		
	//function for common feedback
	@Override
	public CustomerDetails createCommonFeedback(@RequestBody CommonFeedback1 commonFeedback) {
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
		return productRepository.getOne(id).getProductName();
	}
	
	

	

	

}



