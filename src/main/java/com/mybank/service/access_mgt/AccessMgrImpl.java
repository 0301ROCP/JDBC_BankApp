package com.mybank.service.access_mgt;

import java.util.HashMap;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.models.MenuBlock;
import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;

public class AccessMgrImpl implements AccessManager{
	
	final static Logger Log = Logger.getLogger(AccessManager.class);

	private UserDao userDao; //TOASK why do I need this?
	
	//----------CONSTRUCTOR----------
	
	public AccessMgrImpl(UserDao userDao) { //TOASK why do I need this? Do I need this?		
		super();
		this.userDao = userDao;
	}

	
	//-------------METHODS----------
	@Override
	public boolean verifyLogin(int upi, String password) { //STUB
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User createNewUser(boolean isCustomer, boolean isEmployee) { //OBSOLETE
		
		Scanner sc = new Scanner(System.in);
		//UserDaoImpl directory = new UserDaoImpl(); //TODO TOASK shouldn't make a new directory every time, right?
		
		System.out.println("Enter your first name:");
		String firstName = sc.next();
		
		System.out.println("Enter your last name:");
		String lastName = sc.next();
		
		System.out.println("Create a username:");
		String userName = sc.next();
		
		System.out.println("Create a password:");
		String password = sc.next();
		
		System.out.println("Confirm password:"); //TODO verify these match
		String confPassword = sc.next();
		
		User u = new User(-1, userName, firstName, lastName, isCustomer, isEmployee, password);
		
		boolean success = userDao.insertUser(u); //TODO what do if it fails?
		
		User thisUser = userDao.selectUserByUsername(userName);
				
		return thisUser; 

	}

	public boolean verifyMatch(String value1, String column1, String value2, String column2) {
		
		boolean match = false;
		
		User user1 = new User();
			
		int size = userDao.selectUsersByAttribute(value1, column1).size();
		
		if(size == 1) {
			user1 = userDao.selectUsersByAttribute(value1, column1).get(0);	
			String result = userDao.selectStringValueByAttributeByID(user1.getUpi(),column2);
			
			if(result.equals(value2)) {
				match = true;
			}
			
		}
		else {
			System.out.println("AccessMgrImpl: This doesn't work"); //TODO
		}
		
		
			
		return match;
		
	}
	
	@Override
	public boolean verifyExists(String value, String column) {
		boolean exists = false;
		
		int size = userDao.selectUsersByAttribute(value, column).size();		
		
		if(size>0) {
			exists = true;
		}
		
		return exists;
	}


	
	@Override
	public User enterForm(HashMap<String, String> formAnswers, String crudAction) {
		
		int upi = -1;
		String username = username = formAnswers.get("username");
		String first_name = formAnswers.get("first_name");
		String last_name = formAnswers.get("last_name");
		boolean is_customer = false;
		boolean is_employee = false;
		String user_password = formAnswers.get("user_password");

		
		if(formAnswers.get("upi") != null) {
			upi = Integer.parseInt(formAnswers.get("upi"));
		}
		
		if(formAnswers.get("is_customer") != null) {
			is_customer = Boolean.parseBoolean(formAnswers.get("is_customer"));
		}
		
		if(formAnswers.get("is_employee") != null) {
			is_employee = Boolean.parseBoolean(formAnswers.get("is_employee"));
		}



		
		User thisUser = new User(upi,username,first_name,last_name,is_customer,is_employee,user_password);
		
		User toReturn = new User();
		
		switch(crudAction) { //TODO rest of cases
		
		case "create":
			
			Log.debug("Create user: " + thisUser);
			
			userDao.insertUser(thisUser);
			toReturn = userDao.selectUserByUsername(formAnswers.get("username"));
			
			Log.debug("Created user: " + toReturn);
			
			break;
		
		case "read":
			
			toReturn = userDao.selectUserByUsername(formAnswers.get("username"));
			Log.debug("Fetch user by username: " + toReturn);
			
			break;
			
		default:
			Log.fatal("Called EnterForm on CRUD action that does not exist");
		}
		
		
		return toReturn;
			
		
	}

}
