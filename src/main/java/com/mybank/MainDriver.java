package com.mybank;

import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.Controller;
import com.mybank.presentation.view.EmployeeDB;
import com.mybank.presentation.view.Page;


public class MainDriver {
	
	final static Logger Log = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
			
		
		//--------TESTS-----------
		
//		UserDaoImpl uDao = new UserDaoImpl();
//		AccessMgrImpl am = new AccessMgrImpl(uDao);
		
		
		//----Run a particular page:
		
//		Controller controller = new Controller();
//		
//		User testUser = new User();
//		testUser.setUpi(7);
//		
//		controller.setCurrentUser(testUser);
//		
//		Page employeeDB = new EmployeeDB(); //<--Enter page to run here
//		
//		controller.runApp(employeeDB); 
//		
		
		
		//---Test operations:
//		ValidateMoney validator = new ValidateMoney();
//		validator.setUserAnswer("111.222");
//		System.out.println(validator.run());
		
		
		//--------REAL CODE-----------
		
		
		
		Controller appController = new Controller();
//		
		appController.start();
		
		

		
		
	}

}
