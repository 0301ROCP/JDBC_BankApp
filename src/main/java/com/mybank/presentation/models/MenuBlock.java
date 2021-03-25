package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public class MenuBlock extends InteractionBlock{
	
	final static Logger Log = Logger.getLogger(MenuBlock.class);
	
	private LinkedHashMap <String, Button> buttons;
	
	
	//-----------CONSTRUCTOR---------

	public MenuBlock() {
		this.blockType = BlockType.MENU;
		this.instructions = "Type the letter that corresponds to your selection, then hit enter.";
		this.buttons = new LinkedHashMap<String,Button>(); 
	}
	
//	public MenuBlock(LinkedHashMap<String,Button> buttons) {
//		this.blockType = BlockType.MENU;
//		this.instructions = "Type the letter in parentheses on your selected button, then hit enter!";
//		this.buttons = buttons; 
//	}

	
	//-----------GETTERS--------------
	
	public LinkedHashMap<String,Button> getButtons() {
		return buttons;
	}
	
	
	//-------------METHODS------------
	
	public void addButton(String keyStroke, String name, ArrayList<Action> actions) {
		Button thisButton = new Button(keyStroke, name, actions);
		buttons.put(keyStroke,thisButton);
	}
	
	@Override
	public void print() {
		
		for(Map.Entry<String, Button> b : buttons.entrySet()) { //for each button in our set of buttons
			b.getValue().printDashes("_");
			System.out.print("  ");
		}
		
		System.out.println();
		
		for(Map.Entry<String, Button> b : buttons.entrySet()) {
			b.getValue().printMiddle();
			System.out.print(" ");
		}
		
		System.out.println();
		
		for(Map.Entry<String, Button> b : buttons.entrySet()) {
			b.getValue().printDashes("¯");
			System.out.print("  ");
		}
		
		System.out.println();
		
		super.print(); //prints the instructions
		
	}

	@Override
	public ArrayList<Action> run(User currentUser) {
		
		//Log.setLevel(Level.DEBUG);
		Log.debug("MenuBlock run()");
		
		ArrayList<Action> actionQueue = new ArrayList<Action>();
		
		
		boolean validSelection = false;
		String selectedKey;
		Button selectedButton = new Button();
		
		while(!validSelection) {
			selectedKey = sc.nextLine(); //get user input
			
			// Log.debug("User entered: " + selectedKey);
			
			selectedButton = buttons.get(selectedKey.toUpperCase()); //try to find the selected key in the button list
			Log.debug("Select button " + selectedButton);
			
			if(selectedButton != null) { //if it's a valid selection
				validSelection = true;
				//Log.debug("Valid selection");
			}
			else {
				Log.warn("Invalid selection, try again");
				System.out.println("That's not a valid selection. Please try again!");
			}
		}
		
		actionQueue = selectedButton.getActionQueue();		
		
		return actionQueue;
	}

}
