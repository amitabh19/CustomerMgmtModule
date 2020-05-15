package com.example.demo.dao;

import com.capstore.app.models.ProductFeedback;

public interface DAO {
	public boolean createFeedback(ProductFeedback pf);
	public ProductFeedback getFeedbackByProductId(int productId);
}
