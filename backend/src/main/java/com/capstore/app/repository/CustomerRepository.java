package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstore.app.models.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {

}
