package com.mybank.presentation.controller.actions;

import com.mybank.models.User;

public class SetUser extends Action {

	private User user;
	
	
	//-----------CONSTRUCTOR-----------
	public SetUser() {
		super(ActionCategory.SETUSER);
	}
	
	public SetUser(User user) {
		super(ActionCategory.SETUSER);
		this.user = user;		
	}

	
	
	//------------GETTERS-------------

	public User getUser() {
		return user;
	}





	

}
