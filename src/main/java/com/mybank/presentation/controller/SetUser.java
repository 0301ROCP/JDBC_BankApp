package com.mybank.presentation.controller;

import com.mybank.models.User;

public class SetUser extends Action {

	private User user;
	
	
	//-----------CONSTRUCTOR-----------
	
	public SetUser(User user) {
		super(ActionCategory.SETUSER);
		this.user = user;		
	}

	
	
	//------------GETTERS-------------

	public User getUser() {
		return user;
	}





	

}
