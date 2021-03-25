package com.mybank.presentation.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mybank.models.Transaction;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;
import com.mybank.presentation.controller.actions.Back;
import com.mybank.presentation.controller.actions.Navigate;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.MenuBlock;

public class ViewLogs extends Page{
	
	public ViewLogs() {
		this.name = "ViewLogs";
		this.header = "Transaction Log";
		
		this.interactionBlock = new MenuBlock();
		
		((MenuBlock) this.interactionBlock).addButton("B", "Back", Button.makeActionQueue(
				new Back()
				));
	}
	
	@Override
	public ArrayList<Action> run (User currentUser) {
		ArrayList<Action> toReturn = new ArrayList<Action>();
		
		List<Transaction> allTransactions = logDao.selectAllTransactions();
		
		System.out.println(header);
		System.out.println();
		
		for(Transaction t : allTransactions) {
			System.out.println(t);
		}
		
		interactionBlock.print();
		
		toReturn = interactionBlock.run(currentUser);
		
		clear();
		
		return toReturn;
	}
	

}
