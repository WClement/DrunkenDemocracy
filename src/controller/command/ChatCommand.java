package controller.command;

import model.receiver.CommandReceiver;

public abstract class ChatCommand {
	
	// all commands have a reference to their receiver
	// through the superclass
	private CommandReceiver receiver;
	private String suffix;
	private CommandEnum myEnum;
	private int controllerId;
	
	public ChatCommand(String suffix, CommandEnum myEnum, int controllerId) {
		this.setMyEnum(myEnum);
		this.suffix = suffix;
		this.controllerId = controllerId;
	}
	
	public int getControllerId() {
		return controllerId;
	}
	
	public String getSuffix() {
		return suffix;
	}
	
	protected CommandReceiver getReceiver() {
		return receiver;
	}
	
	public void setReceiver(CommandReceiver receiver) {
		this.receiver = receiver;
	}
	
	
	public void execute() {
		this.getReceiver().gameAction(this);
	}

	public CommandEnum getMyEnum() {
		return myEnum;
	}

	public void setMyEnum(CommandEnum myEnum) {
		this.myEnum = myEnum;
	}

}
