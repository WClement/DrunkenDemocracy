package controller;

import controller.command.ChatCommand;
import model.Model;

public class Controller implements Runnable {
	private Thread t;
	private String threadName = "ContThread";
	
	private int controllerId = 0;
	
	CommandParser myParser;
	ChatListenerAdapter chatListener;
	ChannelBot myBot;
	Model myGame;
	
	public void init(Model myGame) {
		this.myGame = myGame;
		myParser = new CommandParser(this);
		chatListener = new ChatListenerAdapter(myParser);
		myBot = new ChannelBot(chatListener);
	}
	
	public void setControllerId(int id) {
		this.controllerId = id;
	}
	
	public int getControllerId() {
		return controllerId;
	}
	
	public void start() {
		System.out.println("Starting controller");
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	public void run() {
		myBot.start();
	}
	
	public void issueCommand(ChatCommand cmd) {
		myGame.receiveCommand(cmd);
	}
	
	public void sendChatMessage(String msg) {
		myBot.sendMessage(msg);
	}

}
