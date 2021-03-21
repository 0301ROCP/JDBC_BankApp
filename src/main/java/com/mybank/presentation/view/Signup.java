package com.mybank.presentation.view;

import java.util.LinkedHashMap;
import java.util.Scanner;

import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.Menu;
import com.mybank.service.access_mgt.AMImpl;
import com.mybank.service.account_mgt.AcctMgrImpl;


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
		
		AcctMgrImpl accountMgr = new AcctMgrImpl();
		AMImpl accessMgr = new AMImpl();
		
		//Ask what kind(s) of accounts they want
		//Buttons: checking, savings, both, back, quit
		//Create stack based on answer
		
		//loop: execute creation of accounts in the stack
		
		int upi = accessMgr.createNewUser(true, false); //TODO change this; should return whole user
		
		//clear(); //clear the console
		return "CustomerDB"; //TODO this is hardcoded, fix it!
	}

}
