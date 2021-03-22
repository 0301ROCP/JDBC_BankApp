package com.mybank.presentation.models;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.mybank.presentation.controller.Action;

public class Button {
	
	private String keyStroke;
	private String name;
	private Queue<Action> actionQueue;

	//-----------CONSTRUCTOR-----------
	
	public Button(String keyStroke, String name, Queue<Action> actionQueue){
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
	
	public Queue<Action> getActionQueue() {
		return actionQueue;
	}
	
	public void setActionQueue() {
		
	}
	

	//-----------METHODS------------
	
	public static Queue<Action> makeActionQueue(Action...a) { //TODO where should this live?
		Queue<Action> thisQueue = new LinkedList<Action>();
		
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
