package model.receiver;

import model.Model;
import controller.command.ChatCommand;

public class VoteReceiver extends CommandReceiver {

	public VoteReceiver(Model myGame) {
		super(myGame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameAction(ChatCommand cmd) {
		super.getGame().voteAction(cmd);
	}

}
