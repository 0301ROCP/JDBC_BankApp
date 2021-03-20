package com.mybank.presentation.models;

import java.util.LinkedHashMap;
import java.util.Map;

import com.mybank.presentation.view.Page;

public class Menu {
	
	private LinkedHashMap <String, Button> buttons;
	private String instructions;
	
	
	public Menu(String instructions) {
		this.buttons = new LinkedHashMap<String,Button>();
		this.instructions = instructions;

	}


	
	public void print() {
		
		for(Map.Entry<String, Button> b : buttons.entrySet()) {
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
		
		System.out.println(instructions);		
		
	}
	
	public void addButton(String keyStroke, String name, String target) {

		Button thisButton = new Button(keyStroke, name, target);
		buttons.put(keyStroke,thisButton);
	}
	
	public void addUtils() {
		Button quitButton = new Button("Q","Quit","Quit");
		Button backButton = new Button("B","Back","Back");
		
		buttons.put(quitButton.getKey(), quitButton);
		buttons.put(backButton.getKey(), backButton);
		
	}
	
	public LinkedHashMap getButtons() {
		return buttons;
	}
	


	
	

}
