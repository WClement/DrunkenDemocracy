package controller;

public class KreygasmCommand extends ChatCommand {



	public KreygasmCommand(String suffix, CommandEnum myEnum) {
		super(suffix, myEnum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		// call the receiver from the superclass
		getReceiver().executeNextCommand();
	}
}
