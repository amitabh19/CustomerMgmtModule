package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstore.app.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}

