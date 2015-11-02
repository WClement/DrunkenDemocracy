package model.receiver;

import model.Game;
import controller.command.ChatCommand;

public class VoteReceiver extends CommandReceiver {

	public VoteReceiver(Game myGame) {
		super(myGame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameAction(ChatCommand cmd) {
		super.getGame().voteAction(cmd);
	}

}
