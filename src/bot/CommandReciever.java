package bot;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public interface CommandReciever {
	
	public abstract void addCommand(ChatCommand chat);
	public abstract Queue<ChatCommand> getQueue();
	public abstract void executeNextCommand();
	public abstract void executeCommand();
}
