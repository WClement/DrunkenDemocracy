package bot;

public abstract class ChatCommand {
	
	CommandReciever reciever;
	
	public ChatCommand(CommandReciever reciever) {
		this.reciever = reciever;
	}
	
	public abstract void execute();
	public abstract String getRawCommand();

}
