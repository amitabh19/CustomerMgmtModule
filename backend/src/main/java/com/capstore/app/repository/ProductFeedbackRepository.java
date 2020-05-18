package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstore.app.models.ProductFeedback;

public interface ProductFeedbackRepository extends JpaRepository<ProductFeedback, Integer> {

}
