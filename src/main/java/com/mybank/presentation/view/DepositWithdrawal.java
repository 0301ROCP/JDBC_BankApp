package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public class DepositWithdrawal extends Page{
	
	final static Logger Log = Logger.getLogger(DepositWithdrawal.class);

	//-----------CONSTRUCTOR-------------
	
	public DepositWithdrawal() {
		super();
		
		this.name = "DepositWithdrawal";
		this.header = "Deposit/Withdrawal Form";
		
		this.interactionBlock = null;
	}
	
	
	@Override
	public ArrayList<Action> run(User currentUser){
		
		//Log.setLevel(Level.DEBUG);
		
		System.out.println(header);
		
		
		//Display accounts		
		ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
				
		Log.debug("# of accounts for this user: " + thisUsersAccounts.size());
		
		int accountCount = 0;
		HashMap<String, Account> accountChoices = new HashMap<String, Account>();
		Account chosenAccount = new Account();
		
		for(Account account: thisUsersAccounts) { //print accounts and create selection menu
			Log.debug(account);
			
			String balanceDollarString = null;
			boolean approved = false;
			
			try {
				balanceDollarString = formatMoney.format(((double) account.getBalanceCents())/100); //TODO pad 0's
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
			
			if(approved && !(balanceDollarString == null)) {
				
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
				System.out.println("Select the number corresponding to the account you'd like to deposit to/withdraw from");
				String selection = sc.nextLine();
				chosenAccount = accountChoices.get(selection); 
				if(chosenAccount != null) {
					valid = true;
				}
				else {
					System.out.println("That's not a valid selection. Please try again.");
				}
			}
			
			System.out.println("Would you like to make a withdrawal or a deposit?");
			System.out.println("(W) Withdrawal");
			System.out.println("(D) Deposit");
			
			valid = false;
			String choice = null;
			while(!valid) {
				choice = sc.nextLine().toUpperCase();
				if(choice.equals("W") || choice.equals("D")){
					valid = true;
				}
				else {
					System.out.println("That's not a valid selection. Please try again.");
				}
			}
			
			String amount = null;		
			
			switch(choice) {
			case "W":
				
				System.out.println("How much would you like to withdraw?");				
				break;
				
			case "D":
				
				System.out.println("How much would you like to deposit?");
				break;
			
			default:
				Log.fatal("How did you even get here? That shouldn't be possible."); //TODO something went wrong
				break;
			}
			
				
			boolean validResponse = false;
			
			while(!validResponse) {
			
				amount = sc.nextLine();				
				moneyValidator.setUserAnswer(amount);
				
				if(moneyValidator.run()) {
					
					if(choice.equals("W")) {
						
						if(chosenAccount.getBalanceCents() >= (int) (Double.parseDouble(amount)*100)) {
							validResponse = true;
						}
						else {
							System.out.println("Insufficient funds to make this withdrawal. Please enter a smaller amount.");
						}
					}
					else {
					
						validResponse = true;
					}
				}
				else {
					System.out.println("Amounts cannot be negative and must follow the format 1234.56. Please try again.");
				}
			}
			
			int toAdd = (int) (Double.parseDouble(amount)*100);
			
			if(choice.equals("W")) {
				toAdd = toAdd * -1;
			}
			
			boolean success = accountManager.addToBalance(chosenAccount, toAdd, currentUser);
			
			if(success) {
				System.out.println("Success!");
			}
			else {
				System.out.println("Something went wrong!");
				Log.error("Failed to update account balance for account " + chosenAccount + " toAdd = " + toAdd);
			}

		}
		
		ArrayList<Action> actionQueue = new ArrayList<Action>(); //may need to add actions for back and create account

		clear(); //clear the console
		
		return actionQueue; 
		
	}
	
}
