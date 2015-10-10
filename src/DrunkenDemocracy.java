import model.Game;
import controller.Controller;


public class DrunkenDemocracy {
	
	static Game theGame;
	static Controller theController;
	
	public static void main(String[] args) {
		/*
		ChannelBot bot1 = new ChannelBot();
		bot1.start();
		*/
		theGame = new Game();
		
		theController = new Controller();
		theController.init(theGame);
		theController.start();
	}
}
