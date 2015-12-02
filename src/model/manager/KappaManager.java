package model.manager;

import java.util.ArrayList;
import java.util.List;

import model.Model;

public class KappaManager implements Manager {

	Model myKingdom;
	List<Kappa> kappaList;
	
	public KappaManager(Model kingdom) {
		this.myKingdom = kingdom;
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
		
	}
	
	

}
