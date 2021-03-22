package com.mybank.presentation.view;

import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;
import com.mybank.presentation.models.NullBlock;

public class CustomerDB extends Page{
	
	public CustomerDB() {
		super();
		
		this.name = "CustomerDB";
		this.header = "Welcome back!"; //TODO add person's name
		
		this.interactionBlock = new NullBlock(); //TEMP
		
//		this.menu.addButton("O", "Log Out", "Logout", Button.makeActionQueue(
//				???));
		//this.menu.addUtils();
	
	}
}
