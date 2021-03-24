package com.mybank.presentation.controller.actions;

import com.mybank.presentation.controller.actions.Action.ActionCategory;

public class Select extends Action{

	private String selection;
	
	
	//-------CONSTRUCTOR---------
	
	public Select(String selection) {
		super(ActionCategory.SELECT);
		this.selection = selection;
	}
	
	
	//----------GETTERS & SETTERS----------
	
	public String getSelection() {
		return selection;
	}


	
	
	//----------TOSTRING-------
	@Override
	public String toString() {
		return "Select [selection=" + selection + "]";
	}

	
}
