package com.mybank;

import com.mybank.models.User;
import com.mybank.presentation.controller.Controller;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;


public class MainDriver {
	
	public static void main(String[] args) {
				
		
		//--------TESTS-----------
		
//		UserDaoImpl uDao = new UserDaoImpl();
//		AccessMgrImpl am = new AccessMgrImpl(uDao);
		
		
		
		
		
		//--------REAL CODE-----------
		
		Controller appController = new Controller();
//		
		appController.start();
		
		

		
		
	}

}
