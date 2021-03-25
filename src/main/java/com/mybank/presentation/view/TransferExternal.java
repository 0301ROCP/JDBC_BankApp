package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.Transfer;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public class TransferExternal extends Page {
	
	final static Logger Log = Logger.getLogger(TransferExternal.class);
	
	//--------CONSTRUCTOR---------
	
	public TransferExternal(){
	
		super();
		
		this.name = "Transfer Money";
		this.header = "Transfer To/From Another User";
	}
	
		
	//----------METHODS--------	
	
	@Override
	public ArrayList<Action> run(User currentUser) {
		Log.setLevel(Level.DEBUG);
		
		Log.debug("TransferExternal run()");
		ArrayList<Action> actionQueue = new ArrayList<Action>();
		
		System.out.println(header);
		
		boolean valid = false;
		
		
		//-----Select my account		
		
		Account myAccount = new Account();
		System.out.println("Which account would you like to transfer money to/from?");
		
		//Display accounts		
		ArrayList<Account> myAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
				
		Log.debug("# of accounts for this user: " + myAccounts.size());
		
		int accountCount = 0;
		HashMap<String, Account> accountChoices = new HashMap<String, Account>();
		
		for(Account account: myAccounts) { //print accounts and create selection menu
			Log.debug(account);
			
			double balance = 0;
			boolean approved = false;
			
			try {
				balance = ((double) account.getBalanceCents())/100; //TODO pad 0's
			}
			catch(Exception e) {
				Log.error("Something wrong with this account!! It has no balance."); //TODO
			}
						
			try {
				approved = account.getStatus().equals("approved") && account.isOpen(); //account must be approved and open
			}
			catch(Exception e) {
				Log.error("Something wrong with approval status on this account"); //TODO
			}
			
			if(approved) {
				
				accountCount++;
				accountChoices.put(String.valueOf(accountCount), account); //this is the selection menu
				
				System.out.print("("+accountCount+") ");				
				System.out.print(account.getAccountType() + " Account '" + account.getNickname() + "': ");
				System.out.print("Current Balance = " + balance + "  ");
				System.out.println();				
			}			
		}
				
		
		if(accountCount == 0) {
			System.out.println("You have no approved open accounts.");
			clear();
			return actionQueue;		
		}
		
		else {
			valid = false;
		
			while(!valid) {
				System.out.println("Select the number corresponding to the account you'd like to transfer to/from");
				String selection = sc.nextLine();

				myAccount = accountChoices.get(selection);
				
				if(myAccount != null) {
					valid = true;
				}
				else {
					System.out.println("That's not a valid selection. Please try again.");
				}			
			}
			
		}	
		
		//----Get current balance of my account
		int myBalanceCents = myAccount.getBalanceCents();
		String myBalanceDollarString = String.valueOf(((double) myBalanceCents)/100);
			
		
		//-------Set primary owner field of my account (null because of the way it was called, but we'll need this field later)
		myAccount.setPrimaryOwner(currentUser);
		
			
		//----Select their account		
		valid = false;
		
		String theirUsername = null;
		User otherUser = new User();
		
		while(!valid) {
			System.out.println("Enter the username of the person you'd like to transfer money to/from");
			theirUsername = sc.nextLine();
			
			try {
				otherUser = accessManager.lookupUser("username",theirUsername);
				Log.debug("Retrieved user " + otherUser);
				valid = true;
			}
			catch(Exception e) {
				Log.info("Customer entered invalid username for transfer " + theirUsername);
			}
			if(!valid) {
				System.out.println("That Username is not in our system. Please try again.");
			}
		}
		
		
		//----Send or request money?		
		valid = false;
		
		String sendOrRequest = null;
		while(!valid) {
			System.out.println("Would you like to send money or request money?");
			System.out.println("Send Money (S)");
			System.out.println("Request Money (R)");	
			sendOrRequest = sc.nextLine().toUpperCase();
			
			if(sendOrRequest.equals("S") || sendOrRequest.equals("R")) {
				valid = true;
			}
			else {
				System.out.println("That is not a valid resopnse. Please enter S or R.");
			}
		}
		
		
		
		//------How much?
		valid = false;
		
		String amountDollarString = null;
		int amountCents = 0;
		
		while(!valid) {
			System.out.println("Enter an amount to transfer");
			amountDollarString = sc.nextLine();
			
			moneyValidator.setUserAnswer(amountDollarString);
			
			if(moneyValidator.run()) {
				amountCents = (int) (Double.parseDouble(amountDollarString)*100);			
				
				switch(sendOrRequest) {
				case "R":
					valid = true;
					break;
				case "S":
					if(myBalanceCents - amountCents >= 0) {
						valid = true;
					}
					else {
						System.out.println("Your account does not have sufficient funds to transfer this amount.");
					}
					break;
				}
				
			}
			else {
				System.out.println("That is not a valid amount. Money cannot be negative and must be written in the form 1234.56");
			}
		}
		
		if(sendOrRequest.equals("R")) { //if requesting money, make this a negative transfer
			amountCents = amountCents * -1;
		}
		
		
		Transfer thisTransfer = new Transfer(-1, currentUser, otherUser, amountCents, "pending", "", new java.sql.Date(System.currentTimeMillis()), false, myAccount, null);

		
		//------OK?

		
		System.out.println("Please confirm this transfer:");
		
		System.out.println("Transfer from your account '" + myAccount.getNickname() + "' current balance = " + myBalanceDollarString);
		System.out.println("Transfer to user " + otherUser.getFirstName() + " " + otherUser.getLastName() + " (" + otherUser.getUserName()+")");
		System.out.println("Transfer amount: "+amountDollarString); //TODO
		
		System.out.println("Confirm! (Y)");
		System.out.println("Cancel (N)");
		
		valid = false;
		
		String confirm = null;
		while(!valid) {
			confirm = sc.nextLine().toUpperCase();
			
			switch(confirm) {
			case "Y":
				valid = true;
				
				boolean success = transferManager.submitTransfer(thisTransfer);
				if(success) {
					System.out.println("Transfer submitted!");
				}
				else {
					System.out.println("Something went wrong. Transfer not submitted.");
				}
				
				break;
			case "N":
				valid = true;
				System.out.println("Transfer cancelled.");
				break;
			default:
				System.out.println("That is not a valid resopnse. Please enter Y or N.");
				break;
			}
		}
		
		clear();
		
		return actionQueue;
	}

}
