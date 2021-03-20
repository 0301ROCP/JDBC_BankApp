package com.mybank.presentation.view;

public class CustomerDB extends Page{
	
	public CustomerDB() {
		super();
		
		this.name = "CustomerDB";
		this.header = "Welcome back!"; //add person's name
		
		this.menu.addButton("O", "Log Out", "Logout");
		this.menu.addUtils();
	
	}
}
