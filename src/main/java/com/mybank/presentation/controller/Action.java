package com.mybank.presentation.controller;

import java.util.LinkedHashMap;
import java.util.Scanner;

import com.mybank.presentation.models.Button;

public class Action {
	
	public enum ActionCategory {NAVIGATE,FORM,SETUSER}; //TOASK is this where I should define this?
	//public enum InputType {TARGET_KEY}; //TOASK should these be public?	

	protected ActionCategory category;
	//protected InputType expectedInput;

	//--------CONSTRUCTOR-----------

	public Action(ActionCategory category){ //TODO add more parameters to this
		this.category = category;
	}
	
	
	//--------GETTERS---------
	
	public ActionCategory getCategory() {
		return this.category;
	}


	
	
	//-----------TOSTRING---------
	@Override
	public String toString() {
		return "Action [category=" + category + "]";
	}
	
	
//	Action(String name){
//		this.name = name;
//	}
	
	//go to public page
	//go to protected page
	//setActiveUser
	//quit

}
