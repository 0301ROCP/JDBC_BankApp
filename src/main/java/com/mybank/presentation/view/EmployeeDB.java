package com.mybank.presentation.view;

import com.mybank.presentation.models.NullBlock;

public class EmployeeDB extends Page{
	
	//-----------CONSTRUCTOR-------------
	
	public EmployeeDB() {
		super();
		
		this.name = "EmployeeDB";
		this.header = "Employee Dashboard";
		
		this.interactionBlock = new NullBlock();
	}

}
