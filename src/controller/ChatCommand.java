package controller;

import model.CommandReceiver;

public abstract class ChatCommand {
	
	// all commands have a reference to their receiver
	// through the superclass
	private CommandReceiver receiver;
	private String rawText;
	/*
	public ChatCommand(CommandReceiver receiver) {
		this.receiver = receiver;
	}
	*/
	
	public ChatCommand(String rawText) {
		this.rawText = rawText;
	}
	
	protected String getRawText() {
		return rawText;
	}
	
	protected CommandReceiver getReceiver() {
		return receiver;
	}
	
	protected void setReceiver(CommandReceiver receiver) {
		this.receiver = receiver;
	}
	
	public abstract void execute();
	public abstract String getRawCommand();

}
