package com.capstore.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capstore.app.models.Cart;
import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.MerchantDetails;
import com.capstore.app.models.Order;
import com.capstore.app.models.Product;
import com.capstore.app.models.ProductFeedback;
import com.capstore.app.models.User;
import com.capstore.app.models.UserAddress;
import com.capstore.app.repository.CustomerRepository;
import com.capstore.app.repository.MerchantRepository;
import com.capstore.app.repository.ProductFeedbackRepository;
import com.capstore.app.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private ProductFeedbackRepository productFeedbackRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//merchant created
				MerchantDetails merchant1 = new MerchantDetails("Merchant 1", "merchant1", "m1234", "merchant1@gmail.com", "Merchant", true, "What is your age", "21", "7895421237", null, null, null, null, null, true, 4);
				MerchantDetails merchant2 = new MerchantDetails("Merchant 2", "merchant2", "m2234", "merchant2@gmail.com", "Merchant", true, "What is your age", "21", "7895221222", null, null, null, null, null, true, 3);
				
				merchantRepository.save(merchant1);
				merchantRepository.save(merchant2);
				
				//user created
				CustomerDetails customer1 = new CustomerDetails("Customer1", "customer1", "customer1", "customer1@gmail.com", "Customer", true, "Favourite Football team?", "Bayern", "9873426712", null, null, null, null, null, null, null, null);
				CustomerDetails customer2 = new CustomerDetails("Customer2", "customer2", "customer2", "customer2@gmail.com", "Customer", true, "Favourite Football team?", "Bayern", "9872426212", null, null, null, null, null, null, null, null);
				
				customerRepository.save(customer1);
				customerRepository.save(customer2);
				
				//add product to merchants
				Product product1 = new Product("Product1", "./assets/product1.jpg", 500, 4, 2, "Brand1", 100, "Product Info", 2, "Category1", true, true, true);
				Product product2 = new Product("Product2", "./assets/product2.jpg", 550, 3, 2, "Brand2", 120, "Product Info2", 2, "Category2", true, true, true);
				Product product3 = new Product("Product3", "./assets/product3.jpg", 505, 4, 2, "Brand3", 130, "Product Info3", 2, "Category3", true, true, true);
				Product product4 = new Product("Product4", "./assets/product4.jpg", 4545, 4, 2, "Brand3", 230, "Product Info4", 2, "Category3", true, true, true);
				Product product5 = new Product("Product5", "./assets/product5.jpg", 5025, 4, 2, "Brand2", 420, "Product Info5", 2, "Category2", true, true, true);
				Product product6 = new Product("Product6", "./assets/product6.jpg", 605, 4, 2, "Brand2", 330, "Product Info6", 2, "Category2", true, true, true);
				Product product7 = new Product("Product7", "./assets/product7.jpg", 400, 4, 2, "Brand1", 220, "Product Info7", 2, "Category2", true, true, true);
				Product product8 = new Product("Product8", "./assets/product8.jpg", 800, 4, 2, "Brand1", 130, "Product Info8", 2, "Category1", true, true, true);
				Product product9 = new Product("Product9", "./assets/product9.jpg", 105, 2, 2, "Brand2", 430, "Product Info9", 2, "Category1", true, true, true);
				Product product10 = new Product("Product10", "./assets/wallet.jpg", 2305, 1, 2, "Brand3", 900, "Product Info10", 2, "Category1", true, true, true);
				Product product11 = new Product("Product11", "./assets/product11.jpg", 1505, 4, 2, "Brand3", 100, "Product Info11", 2, "Category3", true, true, true);
				
				
				Set<Product> merchant1Product = new HashSet<Product>();
				merchant1Product.add(product1);
				merchant1Product.add(product2);
				merchant1Product.add(product4);
				merchant1Product.add(product5);
				merchant1Product.add(product6);
				
				Set<Product> merchant2Product = new HashSet<Product>();
				merchant2Product.add(product3);
				merchant2Product.add(product7);
				merchant2Product.add(product8);
				merchant2Product.add(product9);
				merchant2Product.add(product10);
				merchant2Product.add(product11);
				
				merchant1.setProducts(merchant1Product);
				merchant2.setProducts(merchant2Product);
				
				merchantRepository.save(merchant1);
				merchantRepository.save(merchant2);
				
				
				//add user address
				UserAddress address= new UserAddress("ABC", "DEF", "Indore", "MP", "Peepal ka ped", 12345);
				Set<UserAddress> addressSet = new HashSet<UserAddress>();
				addressSet.add(address);
				customer1.setAddresses(addressSet);
				customerRepository.save(customer1);
				
				// add merchant address
				UserAddress address1 = new UserAddress("CAB", "EWS", "Pune", "Maharashtra", "Corona Gali", 32145);
				Set<UserAddress> addressSet1 = new HashSet<UserAddress>();
				addressSet1.add(address1);
				merchant1.setAddresses(addressSet1);
				merchantRepository.save(merchant1);
				
				//add product in order
				Map<Integer,Integer> order1Product = new HashMap<Integer,Integer>();
				order1Product.put(1, 10);
				order1Product.put(2, 11);
				order1Product.put(3, 20);
				//add order in customer
				Order orders=new Order(300,"Shipped",null,1,null);
				orders.setProducts(order1Product);
				orderRepository.save(orders);
				Set<Order> orderSet1 = new HashSet<Order>();
				orderSet1.add(orders);
				customer1.setOrders(orderSet1);
				customerRepository.save(customer1);
				
	}
	
	
}
