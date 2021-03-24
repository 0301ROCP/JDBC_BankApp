package com.mybank.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account {
	
	private int accountID;
	private String accountType;
	private User primaryOwner;
	private String nickname;
	private boolean jointAccount;
	private ArrayList<User> jointOwners;	
	private Date dateCreated;	
	private int balanceCents;
	private User approvedBy;
	private boolean open;
	private String status;
	
	//TODO add a log
	
	
	//-----------CONSTRUCTORS----------
	
	public Account(){
		
		super();
		
//		this.accountID = -1;		
//		this.dateCreated = new Date(System.currentTimeMillis());
//		this.open = false;
//		
		//Ask if joint
		//make stack of other users
		//set dateCreated to now
		//Enter starting money
		
	}

	public Account(int accountID, String accountType, User primaryOwner, String nickname, boolean jointAccount, ArrayList<User> jointOwners,
			java.sql.Date dateCreated, int balanceCents, User approvedBy, boolean open, String status) {
		
		this.accountID = accountID;
		this.accountType = accountType;
		this.primaryOwner = primaryOwner;
		this.nickname = nickname;
		this.jointAccount = jointAccount;
		this.jointOwners = jointOwners;
		this.dateCreated = dateCreated;
		this.balanceCents = balanceCents;
		this.approvedBy = approvedBy;
		this.open = open;
		this.status = status;
	}


	
	
	//-----------GETTERS & SETTERS----------


	public int getAccountID() {
		return accountID;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public User getPrimaryOwner() {
		return primaryOwner;
	}

	public void setPrimaryOwner(User primaryOwner) {
		this.primaryOwner = primaryOwner;
	}

	public boolean isJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(boolean jointAccount) {
		this.jointAccount = jointAccount;
	}

	public ArrayList<User> getJointOwners() {
		return jointOwners;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public int getBalanceCents() {
		return balanceCents;
	}

	public void setBalanceCents(int balanceCents) {
		this.balanceCents = balanceCents;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getNickname() {
		return nickname;
	}
	
	public String getStatus() {
		return status;
	}
	
	//-------------TOSTRING-----------

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountType=" + accountType + ", primaryOwner=" + primaryOwner
				+ ", nickname=" + nickname + ", jointAccount=" + jointAccount + ", dateCreated=" + dateCreated
				+ ", balanceCents=" + balanceCents + ", approvedBy=" + approvedBy + ", open=" + open + ", status="
				+ status + "]";
	}
	
	


	
	
	
}
