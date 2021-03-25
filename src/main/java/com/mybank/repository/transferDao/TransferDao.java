package com.mybank.repository.transferDao;

import java.util.ArrayList;

import com.mybank.models.Transfer;

public interface TransferDao {
	
	//--------CREATE---------
	
	public boolean insertTransfer(Transfer transfer);

	
	//--------READ---------

	public Transfer selectTransferByTransferID(int transferID);
	public ArrayList<Transfer> selectAllTransfersBySenderUpi(int upi);
	public ArrayList<Transfer> selectAllTransfersByReceiverUpi(int upi);
	
	
	//--------UPDATE---------
	
	public boolean updateNotified(int transferID, boolean notified);
	public boolean updateAccepted(int transferID, String accepted);
	public boolean updateReceiverAccount(int transferID, int receiverAccount);
	
	//--------DELETE---------
	
}
