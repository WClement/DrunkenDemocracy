package model;

import controller.ChatCommand;

public class Game {

	public void receiveCommand(ChatCommand cmd) {
		System.out.println(cmd.getRawCommand());
	}
	
}
