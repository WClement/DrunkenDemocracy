package bot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class ChannelBot {

	PircBotX myBot; //pircBotX
	Configuration config; //pircBotX config file
	ChatListenerAdapter chatListener; //this bot's chatListener 
	
	Properties properties; // properties object
	String user;
	String pass;
	String hostname;
	int port;
	String channel;
	
	// configures new bot and tells it's listener
	public ChannelBot() {
		myBot = new PircBotX(configure());
	}
	
	public void start() {
		try {
			myBot.startBot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ChatListenerAdapter createChatListenerAdapter() {
		if (chatListener == null) {
			try {
				chatListener = new ChatListenerAdapter();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chatListener.setBot(this);
		}
		return chatListener;
	}
	
	public PircBotX getPircBot() {
		return myBot;
	}
	
	public String getChannel() {
		return channel;
	}
	
	// sets properties from config.properties
	// and sets a listener with createChatListenerAdapter()
	// returns config
	private Configuration configure() {
		InputStream in = null;
		properties = new Properties();
		try {
			in = new FileInputStream("config.properties");

			properties.load(in);

			user = properties.getProperty("user");
			pass = properties.getProperty("pass");
			hostname = properties.getProperty("hostname");
			port = Integer.parseInt(properties.getProperty("port"));
			channel = properties.getProperty("channel");
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		config = new Configuration.Builder()
		.addAutoJoinChannel("#" + channel)
		.setName(user)
		.addListener(createChatListenerAdapter())
		.buildForServer(hostname, port, pass);

		return config;
	}
}
