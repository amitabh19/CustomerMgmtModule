package com.capstore.app.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name = "transaction_id")
	private int transactionId;
	
    @Column(name = "transaction_date")
    private Date transactionDate;
   
    @Column(name = "transaction_money")
    private int transactionMoney;
   
    @Column(name = "transaction_method")
    private String transactionMethod;  //(“Credit”,”Debit”,”UPI”,”Wallet”)
   
    @Column(name = "transaction_status")
    private String transactionStatus;   //(“Success”,”Fail”,”Pending”)
    
    
    
	public Transaction(Date transactionDate, int transactionMoney, String transactionMethod, String transactionStatus) {
		super();
		this.transactionDate = transactionDate;
		this.transactionMoney = transactionMoney;
		this.transactionMethod = transactionMethod;
		this.transactionStatus = transactionStatus;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getTransactionMoney() {
		return transactionMoney;
	}
	public void setTransactionMoney(int transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	public String getTransactionMethod() {
		return transactionMethod;
	}
	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Transaction() {
	}
    
}
