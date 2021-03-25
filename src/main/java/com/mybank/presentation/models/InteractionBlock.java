package com.mybank.presentation.models;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import com.mybank.models.User;
import com.mybank.presentation.controller.actions.Action;

public abstract class InteractionBlock {

	protected enum BlockType {MENU, FORM, NULL};
	protected BlockType blockType;
	protected String instructions;
	protected ArrayList<Action> actionQueue;
	final protected Scanner sc = new Scanner(System.in);
	
	
	//------------GETTERS-------------
	
	public BlockType getType() {
		return blockType;
	}
	
	public String getInstructions() {
		return instructions;
	}
	
	
	//----------ABSTRACT METHODS-----
	
	public abstract ArrayList<Action> run(User currentUser);
	
	
	//------------METHODS-------------
	
	public void print() {
		System.out.println(instructions);
	}


}
