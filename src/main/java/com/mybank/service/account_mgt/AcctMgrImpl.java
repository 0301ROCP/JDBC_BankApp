package com.mybank.service.account_mgt;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.mybank.models.Account;
import com.mybank.models.CheckingAccount;
import com.mybank.models.SavingsAccount;
import com.mybank.models.User;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;

public class AcctMgrImpl implements AccountManager{

	@Override
	public Account openAccount(String type) {
				
		Scanner sc = new Scanner(System.in);
		AccountDaoImpl accountDB = new AccountDaoImpl();
		
		Account a = null;
		
		switch(type) { //TODO catch if wrong type
		case "checking":
			a = new CheckingAccount(); //TODO: pass in current user
			break;
		case "savings":
			a = new SavingsAccount();
			break;
		}
				
		
		System.out.println("Would you like to make this a joint account? (Y/N)"); //TODO try/catch
		String isJoint = sc.next();
		
		switch(isJoint) {
		case "Y":
			a.setJointAccount(true);
			break;
		case "N":
			a.setJointAccount(false);
			break;
		} //TODO what if neither Y nor N?
		//TODO if joint, go thru a process to add the other people
		
		
		System.out.println("Enter the starting balance you'd like to deposit in this account");
		String balanceDollarsStr = sc.next(); //TODO verify format (positive dollars, up to 2 decimals)
		double balanceDollars = Double.parseDouble(balanceDollarsStr);
		int balanceCents = (int) balanceDollars * 100;		
		
		a.setBalanceCents(balanceCents);
		
		

		//user
		//jointowners

		
		boolean success = accountDB.insertAccount(a); //TODO what do if it fails?
		
		//Account thisAccount = accountDB.selectAccountBy??(??); //TODO get the account back somehow
		//int accountID = thisAccount.getAccountID();				
		//return accountID; //TODO fix this logic
		
		
		
		return null;
	}

	@Override
	public boolean closeAccount() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
