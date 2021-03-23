package com.mybank.presentation.view;



import java.util.Queue;
import java.util.Scanner;

import com.mybank.presentation.controller.Action;
import com.mybank.presentation.models.InteractionBlock;



public abstract class Page {
	
	String name;
	String header;
	InteractionBlock interactionBlock;
//	Menu menu;
//	String instructions;
	
	protected Scanner sc = new Scanner(System.in);
	
	
	//CONSTRUCTOR:
	public Page() { 
		
	}

	
	//--------GETTERS AND SETTERS---------
	public String getName() {
		return this.name;
	}
	
	public String getHeader() {
		return this.header;
	}
	
	
	//--------METHODS---------
	public void print() { //WORKING
		System.out.println(header);
//		menu.print();		
		interactionBlock.print();
	}
	
//	public String getInput() { //WORKING
//		String input = sc.nextLine();
//		return input;
//	}
	
//	public String getEnteredKey() { //WORKING
//		String key = getInput(); //TODO ADD TRY CATCH INPUT
//		//System.out.println("You selected: "+selection.getName()+".  "); //TODO make this work. Also is name the right field here? Will it always match what the button says?
//		return key;		
//	}
	
	public static void clear() { //WORKING
		pause();
		
		for(int i = 0; i<5; i++) {
			System.out.println();
		}
	}
	
	public static void pause() { //WORKING
		long startTime = System.currentTimeMillis();
		System.out.println("redirecting...");
        while(startTime >= System.currentTimeMillis() - 500); // do nothing
       
	}
	
//	public Queue<Action> getActionsFromButton(String enteredKey) {
//		LinkedHashMap<String,Button> theseButtons = menu.getButtons(); //returns all the buttons in this menu
//		Button selectedButton = theseButtons.get(enteredKey); //get button based on user-entered key
//		Queue<Action> resultingActions = selectedButton.getActionQueue(); //get the action queue that corresponds to clicking this button
//		
//		//String pageTarget = selectedButton.getTarget(); //get target page of selected button
//		
//		return resultingActions;	
//	}
	
//	public Queue<Action> getActionsFromForm(){
//		Queue<Action> resultingActions 
//		
//		return resultingActions;
//	}
	
	
	
	public Queue<Action> run() { //WORKING
		print(); //print this page's header and action block
				
		Queue<Action> actionQueue = interactionBlock.run();

		clear(); //clear the console
		
		return actionQueue; //return the target of the button
	}

}
