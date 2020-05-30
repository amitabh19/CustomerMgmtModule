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
import com.capstore.app.repository.OrderRepository;
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
				Product product1 = new Product("Nike Shoes-Red", "./assets/product1.jpg", 500, 4, 2, "Brand1", 100, "Product Info", 2, "Footwear", true, true, true);
				Product product2 = new Product("Mens shoes-white", "./assets/product2.jpg", 550, 3, 2, "Brand2", 120, "Product Info2", 2, "Footwear", true, true, true);
				Product product3 = new Product("Black Mens Shoes", "./assets/product3.jpg", 505, 4, 2, "Brand3", 130, "Product Info3", 2, "Footwear", true, true, true);
				Product product4 = new Product("Black and white mens shoes", "./assets/product4.jpg", 4545, 4, 2, "Brand3", 230, "Product Info4", 2, "Footwear", true, true, true);
				Product product5 = new Product("Mens shoes-yellow", "./assets/product5.jpg", 5025, 4, 2, "Brand2", 420, "Product Info5", 2, "Footwear", true, true, true);
				Product product6 = new Product("White mens shoes", "./assets/product6.jpg", 605, 4, 2, "Brand2", 330, "Product Info6", 2, "Footwear", true, true, true);
				Product product7 = new Product("Mens Shirt-navy blue-formal-XL", "./assets/product7.jpg", 400, 4, 2, "Brand1", 220, "Product Info7", 2, "Clothing", true, true, true);
				Product product8 = new Product("Dark Blue mens shirt-L", "./assets/product8.jpg", 800, 4, 2, "Brand1", 130, "Product Info8", 2, "Clothing", true, true, true);
				Product product9 = new Product("Light blue mens shirt-L", "./assets/product9.jpg", 105, 2, 2, "Brand2", 430, "Product Info9", 2, "Clothing", true, true, true);
				Product product10 = new Product("Mens wallet", "./assets/wallet.jpg", 2305, 1, 2, "Brand3", 900, "Product Info10", 2, "Accessories", true, true, true);
				Product product11 = new Product("Checked mens shirt size-XL", "./assets/product11.jpg", 1505, 4, 2, "Brand3", 140, "Product Info11", 2, "Clothing", true, true, true);
				Product product12 = new Product("Black and Turquoise mens shirt size-L/XL", "./assets/product12.jpg", 1500, 4, 2, "Brand4", 125, "Product Info12", 2, "Clothing", true, true, true);
				Product product13 = new Product("Violet mens shirt size-L", "./assets/product13.jpg", 1700, 5, 2, "Brand4", 100, "Product Info13", 2, "Clothing", true, true, true);
				Product product14 = new Product("Brown Mens formal shoes size-42", "./assets/product14.jpg", 750, 3, 2, "Brand3", 150, "Product Info14", 2, "Footwear", true, true, true);
				Product product15 = new Product("Mens shirt-size-L", "./assets/product15.jpg", 850, 4, 2, "Brand2", 180, "Product Info15", 2, "Clothing", true, true, true);
				Product product16 = new Product("Black Mens formal shoes", "./assets/product16.jpg", 1000, 4, 2, "Brand3", 160, "Product Info16", 2, "Footwear", true, true, true);
				Product product17 = new Product("Black Mens Formal shoes-2", "./assets/product17.jpg", 1200, 3, 2, "Brand4", 210, "Product Info17", 2, "Footwear", true, true, true);
				Product product18 = new Product("Red shoes for men", "./assets/product18.jpg", 1750, 4, 2, "Brand2", 310, "Product Info18", 2, "Footwear", true, true, true);
				Product product19 = new Product("White formal shirt size-XL", "./assets/product19.jpg", 1200, 2, 2, "Brand3", 130, "Product Info19", 2, "Clothing", true, true, true);
				Product product20 = new Product("Wallet Dark brown", "./assets/product20.jpg", 1505, 4, 2, "Brand2", 230, "Product Info20", 2, "Accessories", true, true, true);
				Product product21 = new Product("Dark blue shirt size-L", "./assets/product21.jpg", 650, 4, 2, "Brand4", 410, "Product Info21", 2, "Clothing", true, true, true);
				Product product22 = new Product("Black wallet for men", "./assets/product22.jpg", 550, 4, 2, "Brand3", 320, "Product Info22", 2, "Accessories", true, true, true);
				Product product23 = new Product("Mens formal shirt", "./assets/product23.jpg", 1605, 4, 2, "Brand4", 250, "Product Info23", 2, "Clothing", true, true, true);
				Product product24 = new Product("Mens wallet by ABC", "./assets/product24.jpg", 655, 3, 2, "Brand3", 180, "Product Info24", 2, "Accessories", true, true, true);
				Product product25 = new Product("Mens wallet by DEF", "./assets/product25.jpg", 750, 4, 2, "Brand4", 300, "Product Info25", 2, "Accessories", true, true, true);
				Product product26 = new Product("Mens belt brown leather", "./assets/product26.jpg", 745, 4, 2, "Brand3", 200, "Product Info26", 2, "Accessories", true, true, true);
				Product product27 = new Product("Brown mens belt by ABC", "./assets/product27.jpg", 645, 2, 2, "Brand3", 320, "Product Info27", 2, "Accessories", true, true, true);
				Product product28 = new Product("Black mens belt ", "./assets/product28.jpg", 585, 4, 2, "Brand3", 260, "Product Info28", 2, "Accessories", true, true, true);
				Product product29 = new Product("Mens belt", "./assets/product29.jpg", 810, 4, 2, "Brand3", 360, "Product Info29", 2, "Accessories", true, true, true);
				Product product30 = new Product("Black mens wallet", "./assets/product30.jpg", 900, 4, 2, "Brand3", 410, "Product Info30", 2, "Accessories", true, true, true);

				
				Set<Product> merchant1Product = new HashSet<Product>();
				merchant1Product.add(product1);
				merchant1Product.add(product2);
				merchant1Product.add(product4);
				merchant1Product.add(product5);
				merchant1Product.add(product6);
				merchant1Product.add(product12);
				merchant1Product.add(product14);
				merchant1Product.add(product16);
				merchant1Product.add(product18);
				merchant1Product.add(product20);
				merchant1Product.add(product22);
				merchant1Product.add(product24);
				merchant1Product.add(product26);
				merchant1Product.add(product28);
				merchant1Product.add(product30);
				
				Set<Product> merchant2Product = new HashSet<Product>();
				merchant2Product.add(product3);
				merchant2Product.add(product7);
				merchant2Product.add(product8);
				merchant2Product.add(product9);
				merchant2Product.add(product10);
				merchant2Product.add(product11);
				merchant2Product.add(product13);
				merchant2Product.add(product15);
				merchant2Product.add(product17);
				merchant2Product.add(product19);
				merchant2Product.add(product21);
				merchant2Product.add(product23);
				merchant2Product.add(product25);
				merchant1Product.add(product27);
				merchant1Product.add(product29);
				
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
				
				Map<Integer,Integer> order2Product = new HashMap<Integer,Integer>();
				order2Product.put(1, 21);
				order2Product.put(2, 22);
				order2Product.put(3, 10);
				
				
				//add order in customer
				Order orders=new Order(300,"Shipped",null,1,null);
				orders.setProducts(order1Product);
				Order orders1=new Order(500,"Shipped",null,1,null);
				orders1.setProducts(order2Product);
				
				orderRepository.save(orders);
				orderRepository.save(orders1);
				
				Set<Order> orderSet1 = new HashSet<Order>();
				orderSet1.add(orders);
				orderSet1.add(orders1);
				
				customer1.setOrders(orderSet1);
				customerRepository.save(customer1);
				
	}
	
	
}
