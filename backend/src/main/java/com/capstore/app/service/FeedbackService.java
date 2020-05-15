package com.example.demo.service;

import com.capstore.app.models.ProductFeedback;

public interface FeedbackService {
	public boolean createFeedback(ProductFeedback pf);
	public ProductFeedback getFeedbackByProductId(int productId);
}
