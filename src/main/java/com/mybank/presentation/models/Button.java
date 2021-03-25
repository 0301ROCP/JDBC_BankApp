package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.Queue;

import com.mybank.presentation.controller.actions.Action;

public class Button {
	
	private String keyStroke;
	private String name;
	private ArrayList<Action> actionQueue;

	//-----------CONSTRUCTOR-----------
	
	public Button() {
		// TODO Auto-generated constructor stub
	}

	public Button(String keyStroke, String name, ArrayList<Action> actionQueue){
		this.keyStroke = keyStroke;
		this.name = name;
		this.actionQueue = actionQueue;
	}
	
	
	//-----------GETTERS------------


	public String getKey() {
		return keyStroke;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Action> getActionQueue() {
		return actionQueue;
	}
	
	public void setActionQueue() {
		
	}
	

	//----------TOSTRING----------
	
	@Override
	public String toString() {
		return "Button [keyStroke=" + keyStroke + ", name=" + name + ", actionQueue=" + actionQueue + "]";
	}
	
	//-----------METHODS------------
	
	public static ArrayList<Action> makeActionQueue(Action...a) { //TODO where should this live?
		ArrayList<Action> thisQueue = new ArrayList<Action>();
		
		for(Action action: a) {
			thisQueue.add(action);
		}
		
		return thisQueue;
	}
	

	

	public void printDashes(String l) { //WORKING
		StringBuilder line = new StringBuilder(" ");
		int buttonLength = name.length()+4;
		
		for(int d = 0; d<buttonLength+2; d++) {
			line.append(l);
		}
		
		System.out.print(line);		
	}
	
	public void printMiddle() { //WORKING
		StringBuilder line2 = new StringBuilder();		
		line2.append("| " + name + " (" + keyStroke + ") |");
		
		System.out.print(line2);
	}
	
}
