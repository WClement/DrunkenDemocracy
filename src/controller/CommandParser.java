package controller;

public class CommandParser {

	CommandFactory cmdFactory;
	Controller myController;
	
	public CommandParser(Controller myController) {
		this.myController = myController;
		cmdFactory = new CommandFactory();
	}
	
	public void parseForCommand(String raw) {
		myController.issueCommand(cmdFactory.createCommand(raw));
	}
	
}
