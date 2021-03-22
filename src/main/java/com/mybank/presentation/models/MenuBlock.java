package com.mybank.presentation.models;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.mybank.presentation.controller.Action;

public class MenuBlock extends InteractionBlock{
	
	private LinkedHashMap <String, Button> buttons;
	
	
	//-----------CONSTRUCTOR---------

	public MenuBlock() {
		this.blockType = BlockType.MENU;
		this.instructions = "Type the letter in parentheses that corresponds to your selection, then hit enter!";
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
	
	public void addButton(String keyStroke, String name, Queue<Action> actions) {
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
	public Queue<Action> run() {
		
		Queue<Action> actionQueue = new LinkedList<Action>();
		
		String selectedKey = sc.nextLine(); //TODO try catch to validate input
		
		Button selectedButton = buttons.get(selectedKey);
		
		actionQueue = selectedButton.getActionQueue();		
		
		return actionQueue;
	}

}
