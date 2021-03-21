package com.mybank.presentation.view;

import java.util.LinkedHashMap;
import java.util.Scanner;

import com.mybank.presentation.models.Button;
import com.mybank.service.access_mgt.AMImpl;

public class Signup extends Page{
	
	//-------CONSTRUCTOR---------
	public Signup() {
		super();
		
		this.name = "Signup";
		this.header = "Sign Up Today!";
		
		this.menu.addUtils();	
	}
	
	
	//-------METHODS-----------
	@Override
	public String run() {
		print(); //print this page
		
		AMImpl accessMgr = new AMImpl();
		
		int upi = accessMgr.createNewUser(true, false);
		
		//clear(); //clear the console
		return "CustomerDB"; //TODO this is hardcoded, fix it!
	}

}
