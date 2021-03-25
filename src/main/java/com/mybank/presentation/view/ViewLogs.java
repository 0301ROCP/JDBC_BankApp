package com.mybank.presentation.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.mybank.models.Transaction;
import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public class ViewLogs extends Page{
	
	public ViewLogs() {
		this.name = "ViewLogs";
		this.header = "Transaction Log";
	}
	
	@Override
	public Queue<Action> run (User currentUser) {
		Queue<Action> toReturn = new LinkedList<Action>();
		
		List<Transaction> allTransactions = logDao.selectAllTransactions();
		
		System.out.println(header);
		System.out.println();
		
		for(Transaction t : allTransactions) {
			System.out.println(t);
		}
		
		clear();
		
		return toReturn;
	}
	

}
