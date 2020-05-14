package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstore.app.models.CustomerDetails;
import com.capstore.app.models.User;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {

}
