package com.capstore.app.service;

import com.capstore.app.models.ProductFeedback;

public interface FeedbackService {
	public boolean createFeedback(ProductFeedback pf);
	public ProductFeedback getFeedbackByProductId(int productId);
}
