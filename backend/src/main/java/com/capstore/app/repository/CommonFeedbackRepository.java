package com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstore.app.models.CommonFeedback;

public interface CommonFeedbackRepository extends JpaRepository<CommonFeedback, Integer> {

}
