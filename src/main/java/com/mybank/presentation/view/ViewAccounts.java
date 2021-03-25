package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Back;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class ViewAccounts extends Page{

	final static Logger Log = Logger.getLogger(ViewAccounts.class);

	
	//----------CONSTRUCTOR------------
	
	public ViewAccounts(){
		
		this.name = "ViewAccounts";
		this.header = "View User's Accounts";
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("B", "Back", Button.makeActionQueue(
				new Back()
				));
	}
	
	
	//----------METHODS-------------
	
	public ArrayList<Action> run(User currentUser){
		
		Log.setLevel(Level.DEBUG);
		
		//--------Select lookup method
		
		System.out.println("Look up user:");
		System.out.println();
		System.out.println("(I) By User ID");
		System.out.println("(U) By Username");
		
		boolean valid = false;
		String choice = null;
		while(!valid) {
			choice = sc.nextLine().toUpperCase();
			if(choice.equals("I") || choice.equals("U")){
				valid = true;
			}
			else {
				System.out.println("That's not a valid selection. Please try again.");
			}
		}
		
		
		//--------Enter criteria		
		
		switch(choice) {
		case "I":
			System.out.println("Enter a User ID number:");
			break;
		
		case "U":
			System.out.println("Enter a Username:");
			break;
		
		default:
			Log.error("How did you even get here??");
			break;
		}
		
		
		valid = false;
		User thisUser = new User();
	
		
		//--------Validate criteria
		
		while(!valid) {
			
			String userLookup = sc.nextLine();
			
			Log.debug("Entered criteria: " + userLookup);
			
			switch(choice) {
			case "I":
				thisUser = accessManager.lookupUser("upi",userLookup);
				Log.debug("Retrieved user " + thisUser);

				break;
				
			case "U":
				thisUser = accessManager.lookupUser("username",userLookup);
				Log.debug("Retrieved user " + thisUser);

				break;
			}
			
			if(thisUser != null) {
				Log.debug("inside if");
				valid = true;
			}
				
			if(!valid) {
				switch(choice.toUpperCase()) {
				case "I":
					System.out.println("That User ID is not in our system. Please try again.");
					break;
				case "U":
					System.out.println("That Username is not in our system. Please try again.");
					break;
				default:
					Log.error("How did you even get here?");
					break;
				}
			}
		}
		
		
		//------Retrieve accounts
		
		ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(thisUser); //ask Account Manager for all of this user's accounts
		
		
		//---------Print accounts
		
		Log.debug("User's accounts:" + thisUsersAccounts);
		System.out.println(thisUser.getUserName() + " (User ID#" + thisUser.getUpi() + ") " + thisUser.getFirstName() + " " + thisUser.getLastName() + ":");
		
		for(Account account: thisUsersAccounts) { //print accounts
			String balance = formatMoney.format(((double) account.getBalanceCents())/100); //TODO pad 0's
			String status = account.getStatus();
			System.out.println(account);

//			System.out.print(account.getAccountType() + " Account '" + account.getNickname() + "' (" + status + ")");
//			System.out.print(": ");
//			System.out.println("Current Balance = " + formatMoney.format(balance) + "  ");
			
		}
		
		
		ArrayList<Action> actionQueue = new ArrayList<Action>();
		
		interactionBlock.print();
		actionQueue = interactionBlock.run(currentUser);
		
		clear();
		
		return actionQueue;
		
	}
	
}
