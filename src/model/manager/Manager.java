package model.manager;

import model.Model;
import model.receiver.CommandReceiver;
import controller.command.ChatCommand;

public abstract class Manager {
	
	private Model myModel;
	private CommandReceiver myReceiver;
	
	public Manager(Model myModel) {
		this.setMyModel(myModel);
	}
	
	public abstract void manage(); // method called in timer loop for updates
	public abstract void executeCommand(ChatCommand cmd);
	public abstract void incrementTimers();
	
	public void setReceiver(CommandReceiver recv) {
		this.setMyReceiver(recv);
	}
	
	// executes next command in receiver queue
	// returns false if empty
	public boolean executeNextReceiverCommand() {
		return myReceiver.executeNextCommand();
	}
	
	public CommandReceiver getMyReceiver() {
		return myReceiver;
	}
	public void setMyReceiver(CommandReceiver myReceiver) {
		this.myReceiver = myReceiver;
	}

	public Model getMyModel() {
		return myModel;
	}

	public void setMyModel(Model myModel) {
		this.myModel = myModel;
	}
}
