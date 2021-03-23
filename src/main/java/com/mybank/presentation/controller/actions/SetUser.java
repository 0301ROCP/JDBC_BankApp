package com.mybank.presentation.controller.actions;

import com.mybank.models.User;

public class SetUser extends Action {

	private User user;
	
	
	//-----------CONSTRUCTOR-----------
//	public SetUser() {
//		super(ActionCategory.SETUSER);
//	}
	
	public SetUser(User user) {
		super(ActionCategory.SETUSER);
		this.user = user;		
	}

	
	
	//------------GETTERS & SETTERS-------------

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	

	
	//--------------TOSTRING---------------
	
	@Override
	public String toString() {
		return "SetUser [user=" + user + "]";
	}
	

}
