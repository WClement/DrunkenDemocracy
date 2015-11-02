package controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.receiver.KreygasmReceiver;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;


public class ChatListenerAdapter extends ListenerAdapter{

	ChannelBot myBot;
	PrintWriter writer;
	KreygasmReceiver kreyReceiver;
	CommandParser cmdParser;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	public void onGenericMessage(GenericMessageEvent event) {
		// check for commands
		cmdParser.parseForCommand(event.getMessage());
		
		//TODO: Move this into its own
		// log information
		Date messagetime = new Date();
		String curr = timeFormat.format(messagetime) + " " + 
		event.getUser().getNick() + 
		": " + event.getMessage();
//		writer.println(curr);

		System.out.println(curr);
	}
	
	public ChatListenerAdapter(CommandParser cmdParser) {
		this.cmdParser = cmdParser;
	}
	
	public ChatListenerAdapter(CommandParser cmdParser, ChannelBot myBot){
		this.cmdParser = cmdParser;
		this.myBot = myBot;
		setUpLog();
	}
	
	public void setBot(ChannelBot myBot) {
		this.myBot = myBot;
		setUpLog();
	}
	
	private void setUpLog() {
		
		Date theDate = new Date();
		String channel = myBot.getChannel();
		
		String filePathString = channel + "_" + dateFormat.format(theDate) + ".txt";
		
		FileOutputStream f = null;
		try {
			f = new FileOutputStream(filePathString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer = new PrintWriter(f, true);		
	}
	
	
}
