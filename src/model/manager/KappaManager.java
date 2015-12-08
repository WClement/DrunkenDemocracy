package model.manager;

import java.util.ArrayList;
import java.util.List;

import controller.command.ChatCommand;
import model.Model;
import model.receiver.KappaReceiver;

public class KappaManager extends Manager {

	List<Kappa> kappaList;
	
	int kappaCounter = 1;

	
	public KappaManager(Model myModel) {
		super(myModel);
		kappaList = new ArrayList<Kappa>();
		createKappa();
	}
	
	public void createKappa() {
		int x = (int) Math.round(Math.random()*600);
		int y = (int) Math.round(Math.random()*600);
		Kappa newKappa = new Kappa(x, y);
		kappaList.add(newKappa);
	}
	
	public List<Kappa> getKappaList() {
		if (kappaList.size() > 0)
			return kappaList;
		else
			return null;
	}

	@Override
	public void manage() {
		// TODO Auto-generated method stub
		if (kappaCounter >= 1) {
			getMyReceiver().executeNextCommand();
			kappaCounter = 0;
		}
		else {
			kappaCounter++;
		}
		
	}

	@Override
	public void executeCommand(ChatCommand cmd) {
		getMyModel().sendChatMessage("Wow Kappa", cmd.getControllerId());
		createKappa();
	}

	@Override
	public void incrementTimers() {
		// TODO Auto-generated method stub
		
	}
	
	

}
