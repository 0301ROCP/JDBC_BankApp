package com.mybank.presentation.view;


public class Welcome extends Page {

	public Welcome(){ //Constructor
		super();
		
		this.name = "Welcome";
		this.header = "Welcome to MyBank!";
		
		this.menu.addButton("L", "Log In", "Login");
		this.menu.addButton("S", "Sign Up", "Signup");
		this.menu.addButton("G","Continue As Guest", "Guest");
		this.menu.addButton("E", "Employee Portal", "EmployeeDB");
		this.menu.addUtils();
		
		
	}
	
}
