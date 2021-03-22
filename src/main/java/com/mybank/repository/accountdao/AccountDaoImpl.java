package com.mybank.repository.accountdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao{

	//-----------------CREATE METHODS------------
	
	@Override
	public boolean insertAccount(Account a) {
		
		boolean success = false; //TODO check if table exists first, if not create it
		
		String sqlStatement = "INSERT into accounts (primary_owner, joint_account, date_created, balance_in_cents, approved, approved_by, is_open) values "
				+ "(?,?,?,?,?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setInt(1, a.getPrimaryOwner().getUpi());
			pStatement.setBoolean(2, a.isJointAccount());
			pStatement.setDate(3, a.getDateCreated());
			pStatement.setInt(4, a.getBalanceCents());
			pStatement.setBoolean(5, a.isApproved());
			pStatement.setInt(6, a.getApprovedBy().getUpi());
			pStatement.setBoolean(7, a.isOpen());
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			System.out.println("repository.accounts.AccountsDBImpl.java: Something went wrong with this account creation!");
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	//-----------------READ METHODS---------------

	@Override
	public Account selectAccountByID(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> selectAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//-----------------UPDATE METHODS-----------------

	@Override
	public boolean updateAccountBalance(Account a, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePrimaryOwner(Account a, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateJointOwners(Account a, User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateApprovalStatus(Account a, boolean status) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//----------------DELETE METHODS--------------

	@Override
	public boolean closeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
