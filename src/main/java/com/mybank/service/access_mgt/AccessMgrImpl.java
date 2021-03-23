package com.mybank.service.access_mgt;

import java.util.Scanner;

import com.mybank.models.User;
import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;

public class AccessMgrImpl implements AccessManager{

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
	public User createNewUser(boolean isCustomer, boolean isEmployee) { //WORKING
		
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


	
	@Override
	public boolean verifyExists(String value, String column) {
		boolean exists = false;
		
		int size = userDao.selectUsersByAttribute(value, column).size();
		
		
		if(size>0) {
			exists = true;
		}
		
		return exists;
	}

}
