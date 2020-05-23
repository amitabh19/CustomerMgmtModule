package com.capstore.app.service;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.capstore.app.models.Product;

@Service
@AutoConfigurationPackage
public interface ProductService {
	
	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(Product product);

	public Product getProductById(int productId);
	
	public List<Product> ListProducts();
	public Product ListProductsByName(String name);
	int ListProductIdByName(String name);

}
