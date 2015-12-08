package model.manager;

import gameObjects.Unit;

import java.util.ArrayList;

import model.Model;
import controller.command.ChatCommand;

public class UnitManager extends Manager {

	ArrayList<Unit> units;
	
	public final int UNIT_COMMAND_TIME = 60;
	private int unitCommandTimer = 0;
	
	public UnitManager(Model myModel) {
		super(myModel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void manage() {
		// TODO Auto-generated method stub

		if (unitCommandTimer >= UNIT_COMMAND_TIME) { // check for command every 60 frames
			if (executeNextReceiverCommand())
				unitCommandTimer = 0;
		}
		
		incrementTimers();
	}

	@Override
	public void executeCommand(ChatCommand cmd) {
		// TODO Auto-generated method stub
		System.out.println("UNIT COMMAND EXECUTED");
		parseCommand(cmd);
	}

	private void parseCommand(ChatCommand cmd) {
		// TODO Auto-generated method stub
		String check = cmd.getSuffix().trim();

		if (check.matches("[s ][a-zA-Z]")) {
			System.out.println(check);
		}
		//U: s gunner
	}

	@Override
	public void incrementTimers() {
		// TODO Auto-generated method stub
		unitCommandTimer++;
	}

}
