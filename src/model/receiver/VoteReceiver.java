package model.receiver;

import model.Kingdom;
import controller.command.ChatCommand;

public class VoteReceiver extends CommandReceiver {

	public VoteReceiver(Kingdom myGame) {
		super(myGame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameAction(ChatCommand cmd) {
		super.getGame().voteAction(cmd);
	}

}
