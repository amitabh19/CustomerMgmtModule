package com.capstore.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "common_feedback")
public class CommonFeedback {

	@Id
	@Column(name = "feedback_id")
	private int feedbackId;  //(Primary Key)
	@Column(name = "feedback_subject")
    private String feedbackSubject;
	@Column(name = "feedback_message")
    private String feedbackMessage;
	@Column(name = "user_id")
	private int userId;
	
    private int feedbackForUserId;  //(Foreign Key)
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
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
	public int getFeedbackForUserId() {
		return feedbackForUserId;
	}
	public void setFeedbackForUserId(int feedbackForUserId) {
		this.feedbackForUserId = feedbackForUserId;
	}
	public CommonFeedback() {
	}
}
