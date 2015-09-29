package bot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;


public class ChatListenerAdapter extends ListenerAdapter{

	ChannelBot myBot;
	PrintWriter writer;
	CommandReciever reciever;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	public void onGenericMessage(GenericMessageEvent event) {
		Date messagetime = new Date();
		
		String curr = timeFormat.format(messagetime) + " " + 
		event.getUser().getNick() + 
		": " + event.getMessage();
		
		writer.println(curr);
		
		if (curr.contains("Kreygasm")) {
			KreygasmReciever reciever = new KreygasmReciever();

			reciever.addCommand(new KreygasmCommand(reciever));
			reciever.executeNextCommand();
		}
		
		System.out.println(curr);
	}
	
	public ChatListenerAdapter() throws FileNotFoundException, UnsupportedEncodingException {
	}
	
	public ChatListenerAdapter(ChannelBot myBot) throws FileNotFoundException, UnsupportedEncodingException {
		this.myBot = myBot;
		setUpLog();
	}
	
	public void setBot(ChannelBot myBot) {
		this.myBot = myBot;
		try {
			setUpLog();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setUpLog() throws FileNotFoundException, UnsupportedEncodingException {
		
		Date theDate = new Date();
		String channel = myBot.getChannel();
		
		String filePathString = channel + "_" + dateFormat.format(theDate) + ".txt";
		
		FileOutputStream f = new FileOutputStream(filePathString);
		writer = new PrintWriter(f, true);		
	}
	
	/*
	public ChatListenerAdapter(PircBotX myBot, CommandListener com) {
	}
	*/
	
}
