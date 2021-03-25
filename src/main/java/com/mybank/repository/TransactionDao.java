package com.mybank.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Transaction;
import com.mybank.models.Transfer;
import com.mybank.models.User;
import com.mybank.service.transfer_mgt.TransferManagerImpl;
import com.mybank.util.ConnectionFactory;

public class TransactionDao {
	
	final static Logger Log = Logger.getLogger(TransactionDao.class);
	
	//--------------CREATE---------------
	
	public boolean insertTransaction(Transaction transaction) {

			
		boolean success = false;
		boolean transfer = false;
		
		if(transaction.getTransferID() != -1) {
			transfer = true;
		}
		
		String sqlStatement = null;
		
		if(transfer) {
			sqlStatement = "INSERT into transactions (transaction_type, amount_cents, user_id, account_id, transfer_id, transaction_date) values "
				+ "(?,?,?,?,?,?)";
		}
		else {
			sqlStatement = "INSERT into transactions (transaction_type, amount_cents, user_id, account_id, transaction_date) values "
					+ "(?,?,?,?,?)";
		}


		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			
			if(transfer) {
			
				pStatement.setString(1, transaction.getTransactionType());
				pStatement.setInt(2, transaction.getAmountCents());
				pStatement.setInt(3, transaction.getUserID());	
				pStatement.setInt(4, transaction.getAccountID());
				pStatement.setInt(5, transaction.getTransferID());
				pStatement.setDate(6, transaction.getTransactionDate());
			}
			
			else {
				pStatement.setString(1, transaction.getTransactionType());
				pStatement.setInt(2, transaction.getAmountCents());
				pStatement.setInt(3, transaction.getUserID());	
				pStatement.setInt(4, transaction.getAccountID());
				pStatement.setDate(5, transaction.getTransactionDate());
			}
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to insert transaction " + transaction);
			//e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to insert transaction");
		}
		

		return success;
		
		
	}

	
	//-------------READ-------------
	
	public List<Transaction> selectAllTransactions() { //WORKING
		
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		
		String sqlString = "SELECT * FROM transactions";
		
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				allTransactions.add(new Transaction(
						rs.getInt("transaction_id"),
						rs.getString("transaction_type"),
						rs.getInt("amount_cents"),
						rs.getInt("user_id"),
						rs.getInt("account_id"),
						rs.getInt("transfer_id"),
						rs.getDate("transaction_date")));
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select all transactions");
			//e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select all transactions");
		}
		
		return allTransactions;
	}
	
}
