package model.receiver;

import java.util.LinkedList;
import java.util.Queue;

import model.Model;
import model.manager.Manager;
import controller.command.ChatCommand;

public abstract class CommandReceiver {
	
	private Queue<ChatCommand> myQueue;
	private Model myGame;
	private Manager myManager;
	
	public CommandReceiver(Manager myManager) {
		this.setMyManager(myManager);
		myManager.setReceiver(this);
		myQueue = new LinkedList<ChatCommand>();
	}
	
	public void addCommand(ChatCommand cmd) {
		cmd.setReceiver(this);
		myQueue.add(cmd);
	}
	
	public boolean executeNextCommand() {
		if (myQueue.size() > 0){
			myQueue.remove().execute();
			return true;
		}
		else {
			return false;
		}
	}
	
	public void notifyManager(ChatCommand cmd) {
		getMyManager().executeCommand(cmd);
	}
	
	public Queue<ChatCommand> getQueue() {
		return myQueue;
	}
	
	public void setQueue(Queue<ChatCommand> myQueue) {
		this.myQueue = myQueue;
	}
	

	

	public void setGame(Model game) {
		this.myGame = game;
	}
	
	public Model getGame() {
		return myGame;
	}

	public Manager getMyManager() {
		return myManager;
	}

	public void setMyManager(Manager myManager) {
		this.myManager = myManager;
	}
}
