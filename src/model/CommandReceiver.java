package model;

import java.util.LinkedList;
import java.util.Queue;

import controller.ChatCommand;

public abstract class CommandReceiver {
	
	private Queue<ChatCommand> myQueue;
	private Game myGame;
		
	public CommandReceiver(Game myGame) {
		this.myGame = myGame;
		myQueue = new LinkedList<ChatCommand>();
	}
	
	public void addCommand(ChatCommand cmd) {
		cmd.setReceiver(this);
		myQueue.add(cmd);
	}
	
	public void executeNextCommand() {
		myQueue.remove().execute();
	}
	
	public Queue<ChatCommand> getQueue() {
		return myQueue;
	}
	
	public void setQueue(Queue<ChatCommand> myQueue) {
		this.myQueue = myQueue;
	}
	
	public abstract void gameAction(ChatCommand cmd);
	

	public void setGame(Game game) {
		this.myGame = game;
	}
	
	public Game getGame() {
		return myGame;
	}
}
