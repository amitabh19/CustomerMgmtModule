package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstore.app.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
