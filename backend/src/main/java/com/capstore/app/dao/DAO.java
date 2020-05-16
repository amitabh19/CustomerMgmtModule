package com.capstore.app.dao;

import com.capstore.app.models.ProductFeedback;

public interface DAO {
	public boolean createFeedback(ProductFeedback pf);
	public ProductFeedback getFeedbackByProductId(int productId);
}
