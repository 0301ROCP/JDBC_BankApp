package com.mybank.presentation.view;

import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;
import com.mybank.presentation.models.NullBlock;

public class CustomerDB extends Page{
	
	public CustomerDB() {
		super();
		
		this.name = "CustomerDB";
		this.header = "Welcome back!"; //TODO add person's name
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("W", "Withdraw", Button.makeActionQueue(
				new Navigate("Withdraw"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("D", "Deposit", Button.makeActionQueue(
				new Navigate("Deposit"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("T", "Transfer", Button.makeActionQueue(
				new Navigate("Transfer"),
				new Navigate("CustomerDB")
				));
		
		((MenuBlock) this.interactionBlock).addButton("N", "Open A New Account", Button.makeActionQueue(
				new Navigate("SelectAccounts") //SelectAccounts already has an action to navigate back to customerDB
				));
		
		((MenuBlock) this.interactionBlock).addButton("O", "Log Out", Button.makeActionQueue(
				new Navigate("Logout")
				));
		

	
	}
	
	
	//override print: show accounts and balances

}
