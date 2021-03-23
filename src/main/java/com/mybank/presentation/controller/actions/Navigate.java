package com.mybank.presentation.controller.actions;

public class Navigate extends Action{

	
	private String target;
	
	
	//-------CONSTRUCTOR---------
	
	public Navigate(String target) {
		super(ActionCategory.NAVIGATE);
		this.target = target;
	}
	
	
	//----------GETTERS & SETTERS----------
	
	public String getTarget() {
		return target;
	}


	
	
	
	//-----------TOSTRING-------------
	@Override
	public String toString() {
		return "Navigate [target=" + target + "]";
	}
}
