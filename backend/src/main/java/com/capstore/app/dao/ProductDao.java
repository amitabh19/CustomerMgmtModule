package com.capstore.app.dao;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Repository;

import com.capstore.app.models.Product;

@Repository
@AutoConfigurationPackage
public interface ProductDao {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(Product product);

	public Product getProductById(int productId);
	
	public List<Product> ListProducts();
	public Product ListProductsByName(String name);

	int ListProductIdByName(String name);
	
}
