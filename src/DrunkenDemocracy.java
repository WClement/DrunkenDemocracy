import view.View;
import view.ViewThread;
import model.Model;
import controller.Controller;

/*
 * NOTE: If you try to run this and Java tells you it can't find native libraries
 * or some shit, do the following:
 * Right click on "JRE System Library" (beneath the view package, above "Referenced Libraries")
 * Select "Build Path"-> "Configure Build Path"
 * Scroll down to "Native library location", select it
 * Press the "Edit..." button
 * Set location to "RealTwitchBot/lib/lwjgl/native libs"
 */


public class DrunkenDemocracy {
	
	static Model theGame;
	static Controller theController;
	static View theView;
	static ViewThread vthread;
	
	static Thread t;
	
	public static void main(String[] args) {
		
		theController = new Controller();
		theGame = new Model();
		theGame.addController(theController);
		theView = new View(theGame);
//		theGame.setView(theView);
		theController.init(theGame);
		theController.start();
		System.out.println("Starting Game");
		theGame.start();
		vthread = new ViewThread(theView);
		vthread.start();
	}
	
	
}
