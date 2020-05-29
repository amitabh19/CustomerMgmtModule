package com.capstore.app.models;

public class ProductFeedback1 {

	private int userId;
	private String feedbackSubject;
	private String feedbackMessage;
	private int productId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFeedbackSubject() {
		return feedbackSubject;
	}
	public void setFeedbackSubject(String feedbackSubject) {
		this.feedbackSubject = feedbackSubject;
	}
	public String getFeedbackMessage() {
		return feedbackMessage;
	}
	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedbackMessage == null) ? 0 : feedbackMessage.hashCode());
		result = prime * result + ((feedbackSubject == null) ? 0 : feedbackSubject.hashCode());
		result = prime * result + productId;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductFeedback1 other = (ProductFeedback1) obj;
		if (feedbackMessage == null) {
			if (other.feedbackMessage != null)
				return false;
		} else if (!feedbackMessage.equals(other.feedbackMessage))
			return false;
		if (feedbackSubject == null) {
			if (other.feedbackSubject != null)
				return false;
		} else if (!feedbackSubject.equals(other.feedbackSubject))
			return false;
		if (productId != other.productId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	public ProductFeedback1(int userId, String feedbackSubject, String feedbackMessage, int productId) {
		super();
		this.userId = userId;
		this.feedbackSubject = feedbackSubject;
		this.feedbackMessage = feedbackMessage;
		this.productId = productId;
	}
	public ProductFeedback1() {
		super();
	}
	
	
	
}
