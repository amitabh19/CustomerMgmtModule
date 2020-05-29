package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstore.app.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
