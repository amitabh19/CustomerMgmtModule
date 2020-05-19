package com.capstore.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import com.capstore.app.dao.ProductDao;
import com.capstore.app.models.Product;

@Service
@AutoConfigurationPackage
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public Product addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
		
	}

	@Override
	public Product getProductById(int productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> ListProducts() {
		return productDao.ListProducts();
	}

	@Override
	public Product ListProductsByName(String name) {
		return productDao.ListProductsByName(name);
	}

}
