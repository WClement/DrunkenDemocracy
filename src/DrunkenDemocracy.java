import view.View;
import model.Model;
import controller.Controller;


public class DrunkenDemocracy {
	
	static Model theGame;
	static Controller theController;
	static View theView;
	
	public static void main(String[] args) {
		
		theController = new Controller();
		theGame = new Model(theController);
		theView = new View(theGame);
		theGame.setView(theView);
		theController.init(theGame);
		theView.init();
		theController.start();
		theView.start();
		theGame.start();
	}
}
