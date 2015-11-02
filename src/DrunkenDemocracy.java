import model.Game;
import controller.Controller;


public class DrunkenDemocracy {
	
	static Game theGame;
	static Controller theController;
	
	public static void main(String[] args) {
		
		theController = new Controller();
		theGame = new Game(theController);
		theController.init(theGame);
		theController.start();
		theGame.start();
	}
}
