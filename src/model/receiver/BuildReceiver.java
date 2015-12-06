package model.receiver;

import model.Model;
import model.manager.BuildManager;
import controller.command.ChatCommand;

public class BuildReceiver extends CommandReceiver {
	
	public BuildReceiver(BuildManager myManager) {
		super(myManager);
	}
}
