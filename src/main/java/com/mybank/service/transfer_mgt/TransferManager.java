package com.mybank.service.transfer_mgt;

import java.util.ArrayList;

import com.mybank.models.Account;
import com.mybank.models.Transfer;
import com.mybank.models.User;

public interface TransferManager {
	
	public boolean submitTransfer(Transfer transfer);
	
	public ArrayList<Transfer> getReceivedTransferReqs(User currentUser);

	public boolean denyTransfer(Transfer transfer);
	
	public boolean acceptTransfer(Transfer transfer, Account account);
}
