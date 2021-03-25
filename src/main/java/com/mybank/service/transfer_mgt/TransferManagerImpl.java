package com.mybank.service.transfer_mgt;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.Account;
import com.mybank.models.Transfer;
import com.mybank.models.User;
import com.mybank.repository.accountdao.AccountDaoImpl;
import com.mybank.repository.transferDao.TransferDaoImpl;
import com.mybank.repository.userdao.UserDaoImpl;

public class TransferManagerImpl implements TransferManager{
	
	final static Logger Log = Logger.getLogger(TransferManagerImpl.class);
	private static TransferDaoImpl transferDao = new TransferDaoImpl();
	private static AccountDaoImpl accountDao = new AccountDaoImpl();
	private static UserDaoImpl userDao = new UserDaoImpl();
	
	
	//------------CONSTRUCTORS----------
	
	public TransferManagerImpl(TransferDaoImpl transferDao){
		this.transferDao = transferDao;
	}

	
	//-----------METHODS------------
	@Override
	public boolean submitTransfer(Transfer transfer) {
		
		boolean success = transferDao.insertTransfer(transfer);
		
		return success;
	}


	
	@Override
	public ArrayList<Transfer> getReceivedTransferReqs(User currentUser) {
		ArrayList<Transfer> startList = new ArrayList<Transfer>();
		ArrayList<Transfer> toReturn = new ArrayList<Transfer>();
		
		int upi = -1;
		
		try {
			upi = currentUser.getUpi();
			startList = transferDao.selectAllTransfersByReceiverUpi(upi);
		}
		catch(Exception e){
			Log.error("Failed to get pending transfers for user " + currentUser);
		}
		
		for(Transfer thisTransfer: startList) {
			if(thisTransfer.isAccepted().equals("pending")) { //if still pending
				toReturn.add(thisTransfer);
			}
		}
		
		return toReturn;
	}


	@Override
	public boolean denyTransfer(Transfer transfer) {

		boolean success = transferDao.updateAccepted(transfer.getId(), "rejected");
		
		return success;
	}


	@Override
	public boolean acceptTransfer(Transfer transfer, Account receiverAccount) {
		
		Log.setLevel(Level.DEBUG);
		
		Log.debug("receiverAccount: " + receiverAccount);
		
		boolean success1 = false;
		boolean success2 = false;
		boolean success3 = false;
		boolean success4 = false;
		boolean success5 = false;
		boolean success2b = false;
		boolean success3b = false;
		
		int receiverBalanceCents = 0;
		int senderBalanceCents = 0;
		int transferCents = 0;
		int receiverAccountID = -1;
		int senderAccountID = -1;
		Account receiverAccountLive = new Account();
		Account senderAccountLive = new Account();
		
		//make sure we can access all necessary fields
		try {
			receiverAccountID = receiverAccount.getAccountID();			
			receiverAccountLive = accountDao.selectAccountByAccountID(receiverAccountID);
			receiverBalanceCents = receiverAccountLive.getBalanceCents();
			Log.debug("receiverBalanceCents: " + receiverBalanceCents);
			
			senderAccountID = transfer.getSenderAccount().getAccountID();
			senderAccountLive = accountDao.selectAccountByAccountID(senderAccountID);
			senderBalanceCents = senderAccountLive.getBalanceCents();
			Log.debug("senderBalanceCents: " + senderBalanceCents);
			
			transferCents = transfer.getAmountCents();
			Log.debug("transferCents: " + transferCents);
			
			success1 = true;

		}		
		catch(Exception e) {
			Log.error("Failed to retrieve account information for transfer " + transfer + " when trying to approve. Approval aborted.");
			e.printStackTrace();
			return false;
		}
		
		//make sure neither balance will go negative
		
		int newReceiverBalanceCents = receiverBalanceCents + transferCents;
		int newSenderBalanceCents = senderBalanceCents - transferCents;
		
		if(newReceiverBalanceCents >= 0 && newSenderBalanceCents >= 0) {
			success2 = true;
		}
		else {			
			Log.warn("Attempted to approve transfer that would cause one of the users to have a negative balance: " + transfer);
			return false;
		}
		
		//make sure both accounts are still open
		
		boolean receiverStatus = receiverAccountLive.isOpen();
		boolean senderStatus = senderAccountLive.isOpen();
		
		success2b = receiverStatus && senderStatus;
		
		if(!success2b) {
			return false;
		}

		//update approval status on transfer
		success3 = transferDao.updateAccepted(transfer.getId(), "accepted"); //update accepted
		success3b = transferDao.updateReceiverAccount(transfer.getId(), receiverAccountID);
		if(!(success3 && success3b)) {
			Log.error("Failed to update transfer status or receiver account: transfer " + transfer);
		}
		else {
			
		//update balances
			success4 = accountDao.updateAccountBalance(receiverAccountID, newReceiverBalanceCents);
			success5 = accountDao.updateAccountBalance(senderAccountID, newSenderBalanceCents);
		}
		
		return(success1 && success2 && success2b && success3 && success4 && success5);
	}
	
	

}
