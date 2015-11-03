package model.receiver;

import model.Kingdom;
import controller.command.ChatCommand;

public class AttackReceiver extends CommandReceiver {

	public AttackReceiver(Kingdom myGame) {
		super(myGame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameAction(ChatCommand cmd) {
		// TODO Auto-generated method stub
		super.getGame().attackAction(cmd);
	}

}
