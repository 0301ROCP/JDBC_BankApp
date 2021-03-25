package com.mybank.models;

import java.sql.Date;

public class Transaction {
	
	private int transactionID;
	private String transactionType;
	private int amountCents;
	private int userID;
	private int accountID;
	private int transferID;
	private java.sql.Date transactionDate;
	
	
	public Transaction(int transactionID, String transactionType, int amountCents, int userID, int accountID, int transferID,
			Date transactionDate) {
		super();
		this.transactionID = transactionID;
		this.transactionType = transactionType;
		this.amountCents = amountCents;
		this.userID = userID;
		this.accountID = accountID;
		this.transferID = transferID;
		this.transactionDate = transactionDate;
	}


	public int getTransactionID() {
		return transactionID;
	}


	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public int getAmountCents() {
		return amountCents;
	}


	public void setAmountCents(int amountCents) {
		this.amountCents = amountCents;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public int getAccountID() {
		return accountID;
	}


	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}


	public int getTransferID() {
		return transferID;
	}


	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}


	public java.sql.Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(java.sql.Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", transactionType=" + transactionType + ", amountCents="
				+ amountCents + ", userID=" + userID + ", accountID=" + accountID + ", transferID=" + transferID
				+ ", transactionDate=" + transactionDate + "]";
	}


	
	
}
