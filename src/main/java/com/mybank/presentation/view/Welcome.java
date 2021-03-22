package com.mybank.presentation.view;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.mybank.presentation.controller.Action.ActionCategory;
import com.mybank.presentation.controller.Navigate;
import com.mybank.presentation.models.Button;

public class Welcome extends Page {

	
	//---------CONSTRUCTOR----------
	
	public Welcome(){ 
		super();
		
		this.name = "Welcome";
		this.header = "Welcome to MyBank!";
		
		//this.menu.addButton("L", "Log In", "Login");
		this.menu.addButton("S", "Sign Up", "Signup", Button.makeActionQueue(
				new Navigate("Signup")
				));
		
		//this.menu.addButton("G","Continue As Guest", "Guest",new GoToPublicPage("GoToGuest","Guest"));
		//this.menu.addButton("E", "Employee Portal", "EmployeeDB");
		//this.menu.addUtils();
		
		
	}
	
//	@Override
//	public Stack<Action> run(){
//		print(); //print this page
//		
//		String key = getEnteredKey();
//		
//		Action goToPage = new Action();
//		
//	}
	
}
