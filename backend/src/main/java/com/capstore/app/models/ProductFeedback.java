package com.capstore.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_feedback")
public class ProductFeedback {
	@Id
	@GeneratedValue
	@Column(name = "feedback_Id")
	private Integer feedbackId;
	@Column(name = "feedback_subject")
    private String feedbackSubject;
	@Column(name = "feedback_message")
    private String feedbackMessage;
	
	@Column(name="product_id")
	private int productId;

	public ProductFeedback(String feedbackSubject, String feedbackMessage,int productId) {
		super();
		this.feedbackSubject = feedbackSubject;
		this.feedbackMessage = feedbackMessage;
		this.productId = productId;
		
	}


	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
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



	
	public ProductFeedback() {
	} 
     
     
}
