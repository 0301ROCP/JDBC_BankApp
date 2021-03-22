package com.mybank.presentation.view;


import java.util.LinkedHashMap;
import java.util.Queue;
import java.util.Scanner;

import com.mybank.presentation.controller.Action;
import com.mybank.presentation.models.Button;
import com.mybank.presentation.models.Menu;





public abstract class Page {
	
	String name;
	String header;
	Menu menu;
	String instructions;
	Queue<Action> actionQueue;	
	
	protected Scanner sc = new Scanner(System.in);
	
	
	//CONSTRUCTOR:
	public Page() { 
		this.instructions = "Type the right letter then hit enter!";
		this.menu = new Menu(instructions);
		
	}

	
	//--------GETTERS AND SETTERS---------
	public String getName() {
		return this.name;
	}
	
	
	//--------METHODS---------
	public void print() {
		System.out.println(header);
		menu.print();		
	}
	
	public String getInput() {
		String input = sc.nextLine();
		return input;
	}
	
	public String getEnteredKey() {
		String key = getInput(); //TODO ADD TRY CATCH INPUT
		//System.out.println("You selected: "+selection.getName()+".  "); //TODO make this work. Also is name the right field here? Will it always match what the button says?
		return key;		
	}
	
	public Queue<Action> getActionsFromButton(String enteredKey) {
		LinkedHashMap<String,Button> theseButtons = menu.getButtons(); //returns all the buttons in this menu
		Button selectedButton = theseButtons.get(enteredKey); //get button based on user-entered key
		Queue<Action> resultingActions = selectedButton.getActionQueue(); //get the action that corresponds to clicking this button
		
		//String pageTarget = selectedButton.getTarget(); //get target page of selected button
		
		return resultingActions;	
	}
	
	public static void clear() {
		pause();
		
		for(int i = 0; i<5; i++) {
			System.out.println();
		}
	}
	
	public static void pause() {
		long startTime = System.currentTimeMillis();
		System.out.println("redirecting...");
        while(startTime >= System.currentTimeMillis() - 2000); // do nothing
       
	}
	
	
	
	
	
	public Queue<Action> run() { //TODO make this more specific than object
		print(); //print this page //TODO make print more robust? Have it give feedback on what the page needs in terms of actions
		
		Queue<Action> actionSubqueue = getActionsFromButton(getEnteredKey()); //get a user-entered key, then return the action of the corresponding button
		
//		while(!actionSubqueue.isEmpty()) {
//			actionQueue.add(actionSubqueue.poll());
//		}
		
		clear(); //clear the console
		return actionSubqueue; //return the target of the button
	}

}
