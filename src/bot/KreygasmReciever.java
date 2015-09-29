package bot;

import java.util.LinkedList;
import java.util.Queue;

public class KreygasmReciever implements CommandReciever {

	Queue<ChatCommand> commandQueue;

	public KreygasmReciever() {
		commandQueue = new LinkedList<ChatCommand>();
	}

	public void addCommand(ChatCommand newCommand) {
		commandQueue.add(newCommand);
	}

	public void executeNextCommand() {
		if (commandQueue.size() > 0) {
			commandQueue.remove().execute();
		}
	}

	public void executeCommand() {
		System.out.println("\n~~~~~BOT: OOOOH KREYGASM\n");
	}

	@Override
	public Queue<ChatCommand> getQueue() {
		// TODO Auto-generated method stub
		return null;
	}

}
