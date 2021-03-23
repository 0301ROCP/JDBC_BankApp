package com.mybank.service.account_mgt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.repository.accountdao.AccountDao;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDao;
import com.mybank.repository.userdao.UserDaoImpl;

public class AcctMgrImpl implements AccountManager{
	
	private AccountDao accountDao;

	
	//------------CONSTRUCTOR-------------
	
	
	public AcctMgrImpl(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}
	
	
	//-------------METHODS----------------
	
	public void enterForm(HashMap<String, String> formAnswers, String crudAction) {
		
		UserDao userDao = new UserDaoImpl();
		
		int account_id = -1;
		User primary_owner = null;
		boolean joint_account = false; //TODO skipped
		ArrayList<User> joint_owners = null; //TODO skipped
		java.sql.Date date_created = null;
		int balance_cents = 0;
		boolean approved = false;
		User approved_by = null;
		boolean open = false;
		
		if(formAnswers.get("account_id") != null) {
			account_id = Integer.parseInt(formAnswers.get("account_id"));
		}
		
		if(formAnswers.get("primary_owner") != null) {
			int ownerUpi = Integer.parseInt(formAnswers.get("primary_owner"));			
			primary_owner = userDao.selectUserByID(ownerUpi);
		}
		
		if(formAnswers.get("date_created") != null) {
			date_created = java.sql.Date.valueOf(formAnswers.get("date_created"));
		}
		
		if(formAnswers.get("balance_cents") != null) {
			balance_cents = Integer.parseInt(formAnswers.get("balance_cents"));
		}
		
		if(formAnswers.get("approved") != null) {
			approved = Boolean.parseBoolean(formAnswers.get("approved"));
		}
		
		if(formAnswers.get("approved_by") != null) {
			int approverUpi = Integer.parseInt(formAnswers.get("approved_by")); //TODO risky try catch, what if it's not a upi?
			approved_by = userDao.selectUserByID(approverUpi);
		}
		
		if(formAnswers.get("open") != null) {
			open = Boolean.parseBoolean(formAnswers.get("open")); //TODO risky try catch, what if it's not a boolean?
		}
		
		Account thisAccount = new Account(account_id, primary_owner, joint_account, joint_owners, date_created, balance_cents, approved, approved_by, open);
						
		
		switch(crudAction) {
		case "create":
			accountDao.insertAccount(thisAccount);
	
			break;
		}
		
	}
	
	

	@Override
	public Account openAccount(String type) { //OBSOLETE
				
//		Scanner sc = new Scanner(System.in);
//		AccountDaoImpl accountDB = new AccountDaoImpl();
//		
//		Account a = null;
//		
//		switch(type) { //TODO catch if wrong type
//		case "checking":
//			a = new CheckingAccount(); //TODO: pass in current user
//			break;
//		case "savings":
//			a = new SavingsAccount();
//			break;
//		}
//				
//		
//		System.out.println("Would you like to make this a joint account? (Y/N)"); //TODO try/catch
//		String isJoint = sc.next();
//		
//		switch(isJoint) {
//		case "Y":
//			a.setJointAccount(true);
//			break;
//		case "N":
//			a.setJointAccount(false);
//			break;
//		} //TODO what if neither Y nor N?
//		//TODO if joint, go thru a process to add the other people
//		
//		
//		System.out.println("Enter the starting balance you'd like to deposit in this account");
//		String balanceDollarsStr = sc.next(); //TODO verify format (positive dollars, up to 2 decimals)
//		double balanceDollars = Double.parseDouble(balanceDollarsStr);
//		int balanceCents = (int) balanceDollars * 100;		
//		
//		a.setBalanceCents(balanceCents);
//		
//		
//
//		//user
//		//jointowners
//
//		
//		boolean success = accountDB.insertAccount(a); //TODO what do if it fails?
//		
//		//Account thisAccount = accountDB.selectAccountBy??(??); //TODO get the account back somehow
//		//int accountID = thisAccount.getAccountID();				
//		//return accountID; //TODO fix this logic
//		
//		
//		
		return null;
	}

	@Override
	public boolean closeAccount() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
