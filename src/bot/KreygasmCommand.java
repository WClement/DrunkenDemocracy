package bot;

public class KreygasmCommand extends ChatCommand {

	String rawCommand;
	CommandReciever reciever;
	
	public KreygasmCommand(CommandReciever reciever) {
		super(reciever);
		this.reciever = reciever;
	}
	
	@Override
	public String getRawCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		reciever.executeCommand();
	}

}
