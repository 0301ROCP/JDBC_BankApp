package com.mybank.presentation.view;

import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class SelectAccounts extends Page{
	
	//---------CONSTRUCTOR----------
	
	public SelectAccounts(){ 
			super();
			
			this.name = "SelectAccounts";
			this.header = "Select Which Account Type(s) You'd Like To Open";
					
			this.interactionBlock = new MenuBlock();
			
			((MenuBlock) this.interactionBlock).addButton("C", "Checking Account", Button.makeActionQueue(
					new Navigate("CreateChecking"),
					new Navigate("CustomerDB")
					));
			
			((MenuBlock) this.interactionBlock).addButton("S", "Savings Account", Button.makeActionQueue(
					new Navigate("CreateSavings"),
					new Navigate("CustomerDB")
					));
			
			((MenuBlock) this.interactionBlock).addButton("B", "Both Checking And Savings Accounts", Button.makeActionQueue(
					new Navigate("CreateChecking"),
					new Navigate("CreateSavings"),
					new Navigate("CustomerDB")
					));
			
			((MenuBlock) this.interactionBlock).addButton("N", "Neither", Button.makeActionQueue(
					new Navigate("CustomerDB")
					));
		
		}

}
