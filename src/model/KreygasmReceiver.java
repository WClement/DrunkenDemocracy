package model;

import java.util.LinkedList;
import java.util.Queue;

import controller.ChannelBot;
import controller.ChatCommand;

public class KreygasmReceiver implements CommandReceiver {

	Queue<ChatCommand> commandQueue;
	ChannelBot myBot;
	int count;

	public KreygasmReceiver() {
		count = 0;
		commandQueue = new LinkedList<ChatCommand>();
	}
	
	public KreygasmReceiver(ChannelBot myBot) {
		count = 0;
		commandQueue = new LinkedList<ChatCommand>();
		this.myBot = myBot;
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
		count++;
		myBot.sendMessage("BOT: There have been " + count + " Kreygasm s");
		
		System.out.println("\n~~~~~BOT: OOOOH KREYGASM\n");
	}

	@Override
	public Queue<ChatCommand> getQueue() {
		// TODO Auto-generated method stub
		return null;
	}

}
