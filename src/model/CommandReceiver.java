package model;

import java.util.Queue;

import controller.ChatCommand;

public interface CommandReceiver {
	
	public abstract void addCommand(ChatCommand chat);
	public abstract Queue<ChatCommand> getQueue();
	public abstract void executeNextCommand();
	public abstract void executeCommand();
}
