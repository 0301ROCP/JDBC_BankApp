package com.mybank.presentation.view;

import java.util.LinkedHashMap;
import java.util.Scanner;

import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.Menu;
import com.mybank.service.access_mgt.AMImpl;


public class Signup extends Page{
	
	//-------CONSTRUCTOR---------
	public Signup() {
		this.instructions = "Please enter your information below to create an account.";
		this.menu = new Menu(instructions);
		
		this.name = "Signup";
		this.header = "Account Signup Form";
		
		//this.menu.addUtils();	
	}
	
	
	//-------METHODS-----------
	@Override
	public void print() {
		System.out.println(header);
		System.out.println();
		System.out.println(instructions);
	}
	
	@Override
	public String run() {
		print(); //print this page
		
		AMImpl accessMgr = new AMImpl();
		
		int upi = accessMgr.createNewUser(true, false);
		
		//clear(); //clear the console
		return "CustomerDB"; //TODO this is hardcoded, fix it!
	}

}
