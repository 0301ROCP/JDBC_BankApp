package com.mybank.presentation.view;

public class Guest extends Page{
	
	public Guest() {
		this.name = "Guest";
		this.header = "Join My Bank Today!";
		
		this.menu.addButton("S","Sign Me Up!","Signup");
		this.menu.addUtils();
		
	}

}
