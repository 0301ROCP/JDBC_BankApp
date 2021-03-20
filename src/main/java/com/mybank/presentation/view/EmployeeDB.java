package com.mybank.presentation.view;

public class EmployeeDB extends Page{
	
	public EmployeeDB() {
		super();
		
		this.name = "EmployeeDB";
		this.header = "Employee Dashboard"; //add name?
		
		this.menu.addButton("S", "Switch To Personal Dashboard", "Login");
		this.menu.addButton("L", "Log Out", "Logout");
		this.menu.addUtils();
	}

}
