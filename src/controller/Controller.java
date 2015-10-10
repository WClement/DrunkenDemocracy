package controller;

import model.Game;

public class Controller {
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
		myBot.start();
	}
	
	public void issueCommand(ChatCommand cmd) {
		System.out.println(cmd.getRawCommand());
		myGame.receiveCommand(cmd);
	}

}
