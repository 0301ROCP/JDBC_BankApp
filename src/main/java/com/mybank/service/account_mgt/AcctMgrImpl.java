package com.mybank.service.account_mgt;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.mybank.models.Account;
import com.mybank.models.CheckingAccount;
import com.mybank.models.SavingsAccount;
import com.mybank.models.User;
import com.mybank.repository.DirectoryImpl;
import com.mybank.repository.accounts.AccountDBImpl;

public class AcctMgrImpl implements AccountManager{

	@Override
	public Account openAccount(String type) {
				
		Scanner sc = new Scanner(System.in);
		AccountDBImpl accountDB = new AccountDBImpl();
		Account a = null;
		
		System.out.println("Would you like to make this a joint account? (Y/N)"); //TODO try/catch
		String isJoint = sc.next();
		
		System.out.println("Enter the starting balance you'd like to deposit in this account");
		String balanceDollars = sc.next();
		
		switch(type) { //TODO catch if wrong type
		case "checking":
			a = new CheckingAccount(-1,currentUser,isJoint,jointOwners,currentDate,balanceDollars*100,false,null,false); //TODO: should this all live as methods under Account classes? Also how/when to handle the joint account logging in, and the stack of multiple account types, and pass in who the current user is 
			break;
		case "savings":
			a = new SavingsAccount();
			break;
		}

		
		boolean success = directory.insertUser(u); //TODO what do if it fails?
		
		User thisUser = directory.selectUserByUsername(userName);
		int upi = thisUser.getUpi();
				
		return upi; //TODO fix this logic
		
		
		
		return null;
	}

	@Override
	public boolean closeAccount() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
