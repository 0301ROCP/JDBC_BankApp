package com.mybank.repository.accountdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.User;
import com.mybank.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao{
	
	final static Logger Log = Logger.getLogger(AccountDao.class);


	//-----------------CREATE METHODS------------
	
	@Override
	public boolean insertAccount(Account a) {
		
		boolean success = false; //TODO check if table exists first, if not create it
		
		String sqlStatement = "INSERT into accounts (primary_owner, account_type, joint_account, date_created, balance_in_cents, is_open) values "
				+ "(?,?,?,?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			pStatement.setInt(1, a.getPrimaryOwner().getUpi());
			pStatement.setString(2, a.getAccountType());
			pStatement.setBoolean(3, a.isJointAccount());
			pStatement.setDate(4, a.getDateCreated());
			pStatement.setInt(5, a.getBalanceCents());
			pStatement.setBoolean(6, a.isOpen());
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to insert account");
			e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to insert account");
		}
		
		return success;
	}
	
	@Override
	public boolean insertApprovedAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//-----------------READ METHODS---------------

	@Override
	public Account selectAccountByAccountID(int id) {
		Account toReturn = new Account();
		
		String sqlString = "SELECT * FROM accounts WHERE account_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, id);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				toReturn = new Account(
						rs.getInt("account_id"),
						rs.getString("account_type"),
						null, //this is the primary User
						rs.getString("nickname"),
						rs.getBoolean("joint_account"),
						null, //list of joint owners
						rs.getDate("date_created"),
						rs.getInt("balance_in_cents"),
						rs.getBoolean("approved"),
						null, //this is the approvedby User
						rs.getBoolean("is_open")
						);
				Log.debug("Account: "+toReturn);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select account");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select account");
		}
		
		return toReturn;
	}

	@Override
	public ArrayList<Account> selectAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Account> selectAccountsByUpi(int upi){
		
		//Log.setLevel(Level.DEBUG);
		
		ArrayList<Account> toReturn = new ArrayList<Account>();
		
		String sqlString = "SELECT * FROM accounts WHERE primary_owner = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, upi);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account newAccount = new Account(
						rs.getInt("account_id"),
						rs.getString("account_type"),
						null, //this is the primary User
						rs.getString("nickname"),
						rs.getBoolean("joint_account"),
						null, //list of joint owners
						rs.getDate("date_created"),
						rs.getInt("balance_in_cents"),
						rs.getBoolean("approved"),
						null, //this is the approvedby User
						rs.getBoolean("is_open")
						);
				Log.debug("Account: "+newAccount);
				toReturn.add(newAccount);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select accounts");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select accounts");
		}
		
		return toReturn;
		
	}
	
	//-----------------UPDATE METHODS-----------------

	@Override
	public boolean updateAccountBalance(int accountID, int amountCents) {

		boolean success = false;
		
		String sqlString = "UPDATE accounts SET balance_in_cents = ? WHERE account_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, amountCents);
			ps.setInt(2, accountID);
			
			Log.debug("Prepared Statement: " + ps);
			
			ps.execute();
			
			success = true;
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update account balance");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update account balance");
		}
		
		return success;
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
