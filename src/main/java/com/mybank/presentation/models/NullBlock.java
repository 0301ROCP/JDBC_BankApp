package com.mybank.presentation.models;

import java.util.LinkedList;
import java.util.Queue;

import com.mybank.presentation.controller.actions.Action;

public class NullBlock extends InteractionBlock {

	//------------CONSTRUCTOR----------
	public NullBlock() {
		this.blockType = BlockType.NULL;
		this.instructions = "Nothing to see on this page!";
	}
	
	@Override
	public Queue<Action> run() {
		Queue<Action> actionQueue = new LinkedList<Action>();
		return actionQueue;
	}

}
