package com.mybank.repository.transferDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Transfer;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;
import com.mybank.util.ConnectionFactory;

public class TransferDaoImpl implements TransferDao{

	final static Logger Log = Logger.getLogger(TransferDao.class);
	public static UserDaoImpl userDao = new UserDaoImpl();	
	public static AccountDaoImpl accountDao = new AccountDaoImpl();

	//--------CREATE---------

	@Override

	public boolean insertTransfer(Transfer transfer) {
		
		Log.setLevel(Level.DEBUG);
		
		boolean success = false;
		
		String sqlStatement = "INSERT into transfers (transfer_sender, transfer_receiver, amount_in_cents, accepted, memo, date_created, notified_sender, sender_account) values "
				+ "(?,?,?,?,?,?,?,?)";
		
//		String sqlStatement = "INSERT into transfers (amount_in_cents, accepted, memo, date_created, notified_sender, sender_account, transfer_sender, transfer_receiver) values" + "(?,?,?,?,?,?,?,?)";


		try (Connection conn = ConnectionFactory.getConnection()){ //Try with resources block 
			
			PreparedStatement pStatement = conn.prepareStatement(sqlStatement);
			
			int sender = -1;
			int receiver = -1;
			int senderAccount = -1;
			
			try {
				sender = transfer.getSender().getUpi();
			}
			catch(Exception e) {
				Log.error("Transfer "+ transfer + " sender "+ transfer.getSender() + " does not have a UPI");
				return false;
			}
			try {
				receiver = transfer.getReceiver().getUpi();
				Log.debug("receiver = " + receiver);
			}
			catch(Exception e) {
				Log.error("Transfer "+ transfer + " receiver "+ transfer.getSender() + " does not have a UPI");
				return false;
			}	
			try {
				senderAccount = transfer.getSenderAccount().getAccountID();
			}
			catch(Exception e) {
				Log.error("Transfer "+ transfer + " sender account "+ transfer.getSenderAccount() + " does not have an account number");
				return false;
			}
			
			pStatement.setInt(1, sender);
			pStatement.setInt(2, receiver);	
			pStatement.setInt(3, transfer.getAmountCents());
			pStatement.setString(4, transfer.isAccepted());
			pStatement.setString(5, transfer.getMemo());
			pStatement.setDate(6, transfer.getDate());
			pStatement.setBoolean(7, transfer.isNotifiedSender());
			pStatement.setInt(8,senderAccount);
			
//			pStatement.setInt(1,transfer.getAmountCents());
//			pStatement.setBoolean(2, transfer.isAccepted());
//			pStatement.setString(3, transfer.getMemo());
//			pStatement.setDate(4, transfer.getDate());
//			pStatement.setBoolean(5, transfer.isNotifiedSender());
//			pStatement.setInt(6,senderAccount);
//			pStatement.setInt(7, sender);
//			pStatement.setInt(8, receiver);	
			
			pStatement.execute();
			success = true;			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to insert transfer " + transfer);
			e.printStackTrace(); //TODO change this!
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to insert transfer");
		}
		

		return success;
	}

	
	//--------READ---------
	
	@Override
	public Transfer selectTransferByTransferID(int transferID) {
		Transfer toReturn = new Transfer();
		
		String sqlString = "SELECT * FROM transfers WHERE transfer_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, transferID);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				toReturn = new Transfer(
						rs.getInt("transfer_id"),
						userDao.selectUserByID(rs.getInt("transfer_sender")),
						userDao.selectUserByID(rs.getInt("transfer_receiver")),
						rs.getInt("amount_in_cents"),
						rs.getString("accepted"),
						rs.getString("memo"),
						rs.getDate("date_created"),
						rs.getBoolean("notified_sender"),
						accountDao.selectAccountByAccountID(rs.getInt("sender_account")),
						accountDao.selectAccountByAccountID(rs.getInt("receiver_account"))	
						);
			}
			
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select transfer");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select transfer");
		}
		
		return toReturn;
	}

	@Override
	public ArrayList<Transfer> selectAllTransfersBySenderUpi(int upi) {
		ArrayList<Transfer> toReturn = new ArrayList<Transfer>();
		
		String sqlString = "SELECT * FROM transfers WHERE transfer_sender = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, upi);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transfer newTransfer = new Transfer(
						rs.getInt("transfer_id"),
						userDao.selectUserByID(rs.getInt("transfer_sender")),
						userDao.selectUserByID(rs.getInt("transfer_receiver")),
						rs.getInt("amount_in_cents"),
						rs.getString("accepted"),
						rs.getString("memo"),
						rs.getDate("date_created"),
						rs.getBoolean("notified_sender"),
						accountDao.selectAccountByAccountID(rs.getInt("sender_account")),
						accountDao.selectAccountByAccountID(rs.getInt("receiver_account"))	
						);
				toReturn.add(newTransfer);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select trasfers");
			//e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select transfers");
		}
		
		return toReturn;
	}
	
	@Override
	public ArrayList<Transfer> selectAllTransfersByReceiverUpi(int upi) {
		ArrayList<Transfer> toReturn = new ArrayList<Transfer>();
		
		String sqlString = "SELECT * FROM transfers WHERE transfer_receiver = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, upi);
			
			Log.debug("Prepared Statement: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transfer newTransfer = new Transfer(
						rs.getInt("transfer_id"),
						userDao.selectUserByID(rs.getInt("transfer_sender")),
						userDao.selectUserByID(rs.getInt("transfer_receiver")),
						rs.getInt("amount_in_cents"),
						rs.getString("accepted"),
						rs.getString("memo"),
						rs.getDate("date_created"),
						rs.getBoolean("notified_sender"),
						accountDao.selectAccountByAccountID(rs.getInt("sender_account")),
						accountDao.selectAccountByAccountID(rs.getInt("receiver_account"))	
						);
				toReturn.add(newTransfer);
			}
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to select trasfers");
			//e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to select transfers");
		}
		
		return toReturn;
	}

	
	//----------UPDATE--------
	
	@Override
	public boolean updateNotified(int transferID, boolean notified) {
		boolean success = false;
		
		String sqlString = "UPDATE transfers SET notifed_sender = ? WHERE transfer_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setBoolean(1, notified);
			ps.setInt(2, transferID);
			
			Log.debug("Prepared Statement: " + ps);
			
			ps.execute();
			
			success = true;
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update transfer notified");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update transfer notified");
		}
		
		return success;
	}

	@Override
	public boolean updateAccepted(int transferID, String accepted) {
		boolean success = false;
		
		String sqlString = "UPDATE transfers SET accepted = ? WHERE transfer_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setString(1, accepted);
			ps.setInt(2, transferID);
			
			Log.debug("Prepared Statement: " + ps);
			
			ps.execute();
			
			success = true;
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update transfer accepted");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update transfer accepted");
		}
		
		return success;
	}
	


	@Override
	public boolean updateReceiverAccount(int transferID, int receiverAccount) {
		boolean success = false;
		
		String sqlString = "UPDATE transfers SET receiver_account = ? WHERE transfer_id = ?";	
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement(sqlString);
			
			ps.setInt(1, receiverAccount);
			ps.setInt(2, transferID);
			
			Log.debug("Prepared Statement: " + ps);
			
			ps.execute();
			
			success = true;
			
		}catch(SQLException e) {
			Log.error("SQL Exception: failed to update transfer accepted");
			e.printStackTrace(); //TODO take this out eventually
		}
		catch(Exception e) {
			Log.fatal("Other Exception: failed to update transfer accepted");
		}
		
		return success;
	}

}
