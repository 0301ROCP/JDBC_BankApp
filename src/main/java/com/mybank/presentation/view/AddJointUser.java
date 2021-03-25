package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public class AddJointUser extends Page{

	public AddJointUser() {
		this.name = "AddJointUser";
		this.header = "Add Another User To Your Account";
	}
	
	//---------------METHODS------------
	
	@Override
	public ArrayList<Action> run(User currentUser){
		
		ArrayList<Action> actionQueue = new ArrayList<Action>();
	
		ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
		
		Log.debug("# of accounts for this user: " + thisUsersAccounts.size());
		
		int accountCount = 0;
		HashMap<String, Account> accountChoices = new HashMap<String, Account>();
		Account chosenAccount = new Account();
		
		System.out.println("Select the number corresponding to the account you'd like to add another user to");
		
		for(Account account: thisUsersAccounts) { //print accounts and create selection menu
			Log.debug(account);
			
			String balanceDollarString = null;
			boolean open = false;
			
			try {
				balanceDollarString = formatMoney.format(((double) account.getBalanceCents())/100); //TODO pad 0's
			}
			catch(Exception e) {
				Log.error("Something wrong with this account!! It has no balance."); //TODO
			}
						
			try {
				open = account.isOpen(); //account must be approved and open
			}
			catch(Exception e) {
				Log.error("Something wrong with approval status on this account"); //TODO
			}
			
			if(open) {
				
				accountCount++;
				accountChoices.put(String.valueOf(accountCount), account); //this is the selection menu
				
				System.out.print("("+accountCount+") ");				
				System.out.print(account.getAccountType() + " Account '" + account.getNickname() + "': ");
				System.out.print("Current Balance = " + balanceDollarString + "  ");
				System.out.println();				
			}			
		}
				
		
		if(accountCount == 0) {
			System.out.println("You have no approved open accounts.");
			//TODO add actions ok and back		
		}
		
		else {
			
			boolean valid = false;
			while(!valid) {
				
				String selection = sc.nextLine();
				chosenAccount = accountChoices.get(selection); 
				if(chosenAccount != null) {
					valid = true;
				}
				else {
					System.out.println("That's not a valid selection. Please try again.");
				}
			}		
		}
		
		
		// Designate another user
		
		System.out.println("Does the person you're going to add have an account with us? (Y/N)");
		boolean valid = false;
		String choice = null;
		while(!valid) {
			choice = sc.nextLine().toUpperCase();
			if(choice.equals("Y") || choice.equals("N")){
				valid = true;
			}
			else {
				System.out.println("That's not a valid selection. Please try again.");
			}
		}
		
		
		switch(choice) {
		case "Y":
			
			//----Select their account		
			valid = false;
			
			String theirUsername = null;
			User otherUser = new User();
			
			while(!valid) {
				System.out.println("Enter the username of the person you'd like to add to your account:");
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
			
			jointDao.insertRow(otherUser.getUpi(),chosenAccount.getAccountID());
			accountDao.updateJointStatus(chosenAccount.getAccountID(), true);
			
			System.out.println("Success!");

			break;
			
		case "N":
			System.out.println("They will need to sign up for an online account before you can add them to your bank account. Please log out and have them create an account.");
			break;
		
		}
		
		
		
		
		clear();
		
		return actionQueue;		
	}
	
}
