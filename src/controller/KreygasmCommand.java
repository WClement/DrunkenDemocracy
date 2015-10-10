package controller;

public class KreygasmCommand extends ChatCommand {

	String rawCommand;
	
	/*
	public KreygasmCommand(CommandReceiver receiver) {
		super(receiver);
	}
	*/
	
	public KreygasmCommand(String rawText) {
		super(rawText);
	}
	
	@Override
	public String getRawCommand() {
		return null;
	}

	@Override
	public void execute() {
		// call the receiver from the superclass
		getReceiver().executeCommand();
	}
}
