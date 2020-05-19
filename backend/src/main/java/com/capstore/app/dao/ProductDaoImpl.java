package com.capstore.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Repository;

import com.capstore.app.models.Product;
import com.capstore.app.repository.ProductRepository;

@Repository
@AutoConfigurationPackage
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
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
	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId).get();
	}

	@Override
	public Product ListProductsByName(String name) {
		List<Product> list1=productRepository.findAll();
		List<Product> list2=list1.stream().filter(n->n.getProductName().equals(name)).collect(Collectors.toList());
		Product p=list2.get(0);
		return p;
	}

	@Override
	public List<Product> ListProducts() {
		return productRepository.findAll();
		
	}

}
