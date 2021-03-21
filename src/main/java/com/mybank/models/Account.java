package com.mybank.models;

import java.util.ArrayList;
import java.util.Date;

public abstract class Account {
	
	private int accountID;
	private User primaryOwner;
	private boolean jointAccount;
	private ArrayList<User> jointOwners;	
	private Date dateCreated;	
	private double balance;
	private boolean approved;
	private User approvedBy;
	private boolean open;
	
	//TODO add a log
	
	
	//-----------CONSTRUCTOR----------
	
	Account(){
		
		//Ask if joint
		//make stack of other users
		//set dateCreated to now
		//Enter starting money
		
	}
	
	

}
