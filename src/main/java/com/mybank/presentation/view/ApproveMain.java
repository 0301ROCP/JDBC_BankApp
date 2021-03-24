package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Select;

public class ApproveMain extends Page{

	final static Logger Log = Logger.getLogger(ApproveMain.class);	
	
	public ApproveMain() { //this is a redirect page, it shouldn't display anything
		super();
		
		this.name = "ApproveMain";
		this.header = null;
		
		this.interactionBlock = null;
		
	}
	
	
	@Override
	public Queue<Action> run(User currentUser) { //WORKING
		
		Log.setLevel(Level.DEBUG);
		Log.debug("ApproveMain run()");
		
		Queue<Action> actionQueue = new LinkedList<Action>();
		ApproveIndiv approvePage = new ApproveIndiv();
		
		
		ArrayList<Account> pendingAccounts = accountManager.getPendingAccounts(); //ask Account Manager for all unapproved accounts
		
		for(Account thisAccount: pendingAccounts) {
			
			Queue<Action> approveQueue = approvePage.run(currentUser, thisAccount);
			
			for(Action nextAction: approveQueue) { //there should only be one action in the queue, but leaving this as a queue for future flexibility
				
				switch(((Select) nextAction).getSelection()) { //switch on selection: approve, deny, skip
				case "Approve":					
					accountManager.setAccountApproval(thisAccount, "approved", currentUser);					
					break;
				case "Deny":
					accountManager.setAccountApproval(thisAccount, "denied", currentUser);
					break;
				case "Skip":
					break;
				default:
					Log.fatal("Invalid selection");
				}
			}
		}
		
		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}
	
}
