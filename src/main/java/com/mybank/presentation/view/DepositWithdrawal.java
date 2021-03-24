package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.operations.ValidateMoney;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;

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
	public Queue<Action> run(User currentUser){
		
		Log.setLevel(Level.DEBUG);
		
		System.out.println(header);
		
		
		//Display accounts		
		ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
				
		int accountCount = 0;
		HashMap<String, Account> accountChoices = new HashMap<String, Account>();
		Account chosenAccount = new Account();
		
		for(Account account: thisUsersAccounts) { //print accounts and create selection menu
			Log.debug(account);
			
			double balance = 0;
			boolean approved = false;
			
			try {
				balance = account.getBalanceCents()/100; //TODO pad 0's
			}
			catch(Exception e) {
				Log.error("Something wrong with this account!! It has no balance."); //TODO
			}
			
			String accountStatus = account.getStatus();
			if(accountStatus == null) { //TODO this isn't great; should set status to pending when account is first applied for, but that's hard to fix with the formBlock
				accountDao.updateApprovalStatus(account.getAccountID(),"pending");
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
			//TODO add actions ok and back		
		}
		
		else {

			System.out.println("Select the number corresponding to the account you'd like to deposit to/withdraw from");
			String selection = sc.nextLine();
			chosenAccount = accountChoices.get(selection);
			
			System.out.println("Would you like to make a withdrawal or a deposit?");
			System.out.println("(W) Withdrawal");
			System.out.println("(D) Deposit");
			
			boolean valid = false;
			String choice = null;
			while(!valid) {
				choice = sc.nextLine().toUpperCase();
				if(choice.equals("W") || choice.equals("D")){
					valid = true;
				}
				else {
					
				}
			}
			
			ValidateMoney validator = new ValidateMoney();
			String amount = null;
			
			
			switch(choice) {
			case "W":
				
				System.out.println("How much would you like to withdraw?");
				
				break;
			case "D":
				
				System.out.println("How much would you like to deposit?");

				break;
			
			default:
				Log.fatal("How did you even get here? That shouldn't be possible.");
			}
			
			
			
			boolean validResponse = false;
			
			while(!validResponse) {
			
				amount = sc.nextLine();
				
				validator.setUserAnswer(amount);
				if(validator.run()) {
					
					if(choice.equals("W")) {
						
						if(chosenAccount.getBalanceCents() >= (int) Double.parseDouble(amount)*100) {
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
			
			int toAdd = (int) Double.parseDouble(amount)*100;
			
			if(choice.equals("W")) {
				toAdd = toAdd * -1;
			}
			
			
			accountManager.addToBalance(chosenAccount, toAdd);
			
			System.out.println("Success!");

		}
		
		Queue<Action> actionQueue = null; //may need to add actions for back and create account

		clear(); //clear the console
		
		return actionQueue; 
		
	}
	
}
