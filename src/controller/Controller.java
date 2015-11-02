package controller;

import controller.command.ChatCommand;
import model.Game;

public class Controller implements Runnable {
	private Thread t;
	private String threadName = "ContThread";
	
	CommandParser myParser;
	ChatListenerAdapter chatListener;
	ChannelBot myBot;
	Game myGame;
	
	public void init(Game myGame) {
		this.myGame = myGame;
		myParser = new CommandParser(this);
		chatListener = new ChatListenerAdapter(myParser);
		myBot = new ChannelBot(chatListener);
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
