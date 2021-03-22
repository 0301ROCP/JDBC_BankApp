package com.mybank.service.access_mgt;

import java.util.Scanner;

import com.mybank.models.User;
import com.mybank.repository.DirectoryImpl;

public class AMImpl implements AccessManager{

	@Override
	public boolean verifyLogin(int upi, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User createNewUser(boolean isCustomer, boolean isEmployee) { //TOASK should this all go in the constructor?
		
		Scanner sc = new Scanner(System.in);
		DirectoryImpl directory = new DirectoryImpl();
		
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
		
		boolean success = directory.insertUser(u); //TODO what do if it fails?
		
		User thisUser = directory.selectUserByUsername(userName);
				
		return thisUser; //TODO fix this logic

	}

}
