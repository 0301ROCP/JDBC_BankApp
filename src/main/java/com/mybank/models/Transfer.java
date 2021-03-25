package com.mybank.models;

import java.sql.Date;

public class Transfer {
	
	private int id;
	private User sender;
	private User receiver;
	private int amountCents;
	private String accepted;
	private String memo;
	private java.sql.Date date;
	private boolean notifiedSender;
	private Account senderAccount;
	private Account receiverAccount;
	
	
	//---------CONSTRUCTOR---------

	public Transfer() {
		
	}

	public Transfer(int id, User sender, User receiver, int amouuntCents, String accepted, String memo, Date date,
			boolean notifiedSender, Account senderAccount, Account receiverAccount) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amountCents = amouuntCents;
		this.accepted = accepted;
		this.memo = memo;
		this.date = date;
		this.notifiedSender = notifiedSender;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
	}

	
	//---------GETTERS & SETTERS---------
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public int getAmountCents() {
		return amountCents;
	}

	public void setAmountCents(int amouuntCents) {
		this.amountCents = amouuntCents;
	}

	public String isAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public boolean isNotifiedSender() {
		return notifiedSender;
	}

	public void setNotifiedSender(boolean notified_sender) {
		this.notifiedSender = notified_sender;
	}

	
	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	
		
	//---------TOSTRING---------
	
	

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", amouuntCents=" + amountCents
				+ ", accepted=" + accepted + ", memo=" + memo + ", date=" + date + ", notifiedSender=" + notifiedSender
				+ ", senderAccount=" + senderAccount + ", receiverAccount=" + receiverAccount + "]";
	}
	


}
