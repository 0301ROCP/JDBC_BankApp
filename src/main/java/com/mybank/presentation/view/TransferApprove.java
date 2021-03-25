package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.Transfer;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Select;

public class TransferApprove extends Page{

	final static Logger Log = Logger.getLogger(TransferApprove.class);
	
	//------CONSTRUCTOR------
	
	public TransferApprove() {
		this.name = "ApproveTransfers";
		this.header = "View Your Pending Transfers";
	}
	
	
	//----------METHODS---------
	
	@Override
	public Queue<Action> run(User currentUser) { 
		
		Log.setLevel(Level.DEBUG);
		
		Queue<Action> actionQueue = new LinkedList<Action>();
		TransferApproveIndiv approvePage = new TransferApproveIndiv();
		
		
		ArrayList<Transfer> pendingTransfers = transferManager.getReceivedTransferReqs(currentUser); //ask Transfer Manager for all unapproved accounts
		
		for(Transfer thisTransfer: pendingTransfers) {
			
			Queue<Action> approveQueue = approvePage.run(currentUser, thisTransfer);
			
			for(Action nextAction: approveQueue) { //there should only be one action in the queue, but leaving this as a queue for future flexibility
				
				switch(((Select) nextAction).getSelection()) { //switch on selection: approve, deny, skip
				case "Approve":			
					
					//what account do you want to put this in?
					
					ArrayList<Account> thisUsersAccounts = accountManager.getThisUsersAccounts(currentUser); //ask Account Manager for all of this user's accounts
					
					int accountCount = 0;
					HashMap<String, Account> accountChoices = new HashMap<String, Account>();
					Account chosenAccount = new Account();
					
					System.out.println("Select the number corresponding to the account you'd like to use for this transfer");
					
					for(Account account: thisUsersAccounts) { //print accounts and create selection menu
						Log.debug(account);
						
						int potentialNewBalance = account.getBalanceCents() + thisTransfer.getAmountCents();
						if(potentialNewBalance >= 0) { //thisTransfer's balance will be negative if this is a $ request
						
							String balanceDollarString = null;
							boolean approved = false;
							
							try {
								balanceDollarString = formatMoney.format(((double) account.getBalanceCents())/100); 
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
					}							
					
					if(accountCount == 0) {
						System.out.println("You have no approved open accounts with sufficient funds for this transfer.");
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
					
					Log.debug("Chosen account: " + chosenAccount);
					
					boolean successfulTransfer = transferManager.acceptTransfer(thisTransfer, chosenAccount); //Service layer, make the transfer!
					if(successfulTransfer) {
						System.out.println("Success!");
					}
					else {
						System.out.println("Something went wrong.");
					}

					break;
				case "Deny":
					boolean success = transferManager.denyTransfer(thisTransfer);
					if(success) {
						System.out.println("Successfully denied this transfer");
					}
					else {
						System.out.println("Something went wrong");
					}
					break;
				case "Skip":
					break;
				default:
					Log.fatal("Invalid selection");
					break;
				}
			}
		}
		
		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}
	
}
