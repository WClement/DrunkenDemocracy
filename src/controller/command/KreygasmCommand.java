package controller.command;

public class KreygasmCommand extends ChatCommand {



	public KreygasmCommand(String suffix, CommandEnum myEnum, int controllerId) {
		super(suffix, myEnum, controllerId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// call the receiver from the superclass
		getReceiver().executeNextCommand();
	}
}
