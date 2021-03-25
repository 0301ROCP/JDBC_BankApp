package com.mybank.presentation.view;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public class CreateAccount extends Page{

	final static Logger Log = Logger.getLogger(CreateAccount.class);
	
	//-------CONSTRUCTOR---------
	public CreateAccount(String name, String header) {
		super();
		
		this.name = name;
		this.header = header;
		
		this.interactionBlock = null; 			
	}
	
	
	//--------METHODS----------
	
	@Override
	public ArrayList<Action> run(User currentUser){		
		
		ArrayList<Action> actionQueue = new ArrayList<Action>();
		
		System.out.println(header);
		
		String accountType = null;
		String nickname = null;
		User primaryUser = currentUser;
		int amountCents = 0;		
		String status = "pending";
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		boolean isOpen = true; //just means it's not been closed by the user
		
				
		//----Question #1
		boolean valid = false;
		String amountDollars = null;
		
		while(!valid) {
			System.out.println("How much would you like to deposit into this account?");
			amountDollars = sc.nextLine();
			moneyValidator.setUserAnswer(amountDollars); //TODO simplify this down the road
			valid = moneyValidator.run();
			
			if(!valid) {
				System.out.println("The amount must be 0 or more, and follow the format 1234.56. Please try again.");
			}
		}
		
		amountCents = (int) (Double.parseDouble(amountDollars)*100);
		
		
		//----Question #2		
		valid = false;		
		
		while(!valid) {
			System.out.println("What would you like to name this account?");
			nickname = sc.nextLine();
			
			if(nickname.equals("")) {
				System.out.println("You must give this account a name (eg, 'My Checking Account').");
			}
			else {
				valid = true;
			}
		}
		
		
		//-----Set checking or savings
		
		switch(name) {
		case "CreateSavings":
			accountType = "Savings";
			break;
		case "CreateChecking":
			accountType = "Checking";
			break;
		default:
			Log.fatal("CreateAccount page called for neither savings nor checking: called "+name);
		
		}
		
		Account newAccount = new Account(-1, accountType, primaryUser, nickname, false, null, date, amountCents, null, isOpen, status);
		accountManager.openAccount(newAccount);
		
		clear();
		
		return actionQueue;
	}
	
}
