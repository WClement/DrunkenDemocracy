package model.receiver;

import java.util.LinkedList;
import java.util.Queue;

import model.Game;
import controller.command.ChatCommand;

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
	
	public boolean executeNextCommand() {
		if (myQueue.size() > 0){
			myQueue.remove().execute();
			return true;
		}
		else {
			return false;
		}
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
