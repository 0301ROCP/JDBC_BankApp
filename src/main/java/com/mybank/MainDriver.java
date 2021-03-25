package com.mybank;

import org.apache.log4j.Logger;

import com.mybank.presentation.controller.Controller;
import com.mybank.repository.transferDao.TransferDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.service.access_mgt.AccessMgrImpl;


public class MainDriver {
	
	final static Logger Log = Logger.getLogger(MainDriver.class);
	
	public static void main(String[] args) {
			
		
		//--------TESTS-----------
		
		UserDaoImpl uDao = new UserDaoImpl();
		AccessMgrImpl am = new AccessMgrImpl(uDao);
		TransferDaoImpl tDao = new TransferDaoImpl();
		
		
		//----Run a particular page:
//		
//		Controller controller = new Controller();
//		
//		User testUser = new User();
//		testUser.setUpi(7);
//		
//		controller.setCurrentUser(testUser);
//		
//		Page customerDB = new CustomerDB(); //<--Enter page to run here
//		
//		controller.runApp(customerDB); 
		
		
		
		//---Test operations:
//		ValidateMoney validator = new ValidateMoney();
//		validator.setUserAnswer("111.222");
//		System.out.println(validator.run());
		
//		User testUser1 = new User(7, "stcstc", "Gil", "Phish", true, true, "1234");
//		User testUser2 = new User(1, "dummyuser01", "Dummy", "User", true, false, "1234");
//		Account testAccount1 = new Account(14, "Savings", testUser1, "Lucky", false, null, null, 88888, testUser1, true, "approved");
//		Account testAccount2 = new Account(12, "Chhecking", testUser2, "Bob", false, null, null, 3030, testUser2, true, "approved");
//		Transfer testTransfer = new Transfer(-1, testUser1, testUser2, 5000, false, "hi", null, false, testAccount1, testAccount2);
//		
//		tDao.insertTransfer(testTransfer);
		
		
		//--------REAL CODE-----------
		
		
		
		Controller appController = new Controller();
//		
		appController.start();
		

	}

}
