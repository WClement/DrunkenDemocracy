package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

public class ChannelBot {

	PircBotX myBot; //pircBotX
	Configuration config; // config.properties object
	ChatListenerAdapter chatListener; //bot's specific chatListener 
	
	// The properties object will be
	// constructed out of these values
	Properties properties;
	String user;
	String pass;
	String hostname;
	int port;
	String channel = "";
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~Configuration methods~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	/* ChannelBot():
	 * 
	 * Constructor that calls configure() for this
	 * bot's PircBotX
	 * * * * * * * * * * * * * * * * * * * * * * */	
	public ChannelBot(ChatListenerAdapter chatListener) {
		this.chatListener = chatListener;
		myBot = new PircBotX(configure());
	}
	
	/* start():
	 * 
	 * Called from main() to start the bot.
	 * Should only be called once bot is configured.
	 * * * * * * * * * * * * * * * * * * * * * * */
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
		
	/* configure():
	 * 
	 * Sets up the properties object for the new bot.
	 * Reads the info it needs from the config.properties file.
	 * 
	 * RETURNS: Configuration object for bot.
	 * 
	 * SIDE EFFECTS: Creates a chat listener with a call
	 * to createChatListener(). Also sets up variables 
	 * within this bot.
	 * * * * * * * * * * * * * * * * * * * * * * * */
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
		.addListener(chatListener)
		.buildForServer(hostname, port, pass);

		return config;
	}
	
	/* sendMessage():
	 * 
	 * Writes a message in the chat.
	 * 
	 * NOTE: only works in my channel to prevent getting banned!
	 * * * * * * * * * * * * * * * * * * * * * * * */
	public void sendMessage(String message) {
		if (channel.equals("jimmynojohns")) {
			myBot.send().message("#" + channel, message);
		}
	}
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/*~~~~~~~Getters & Setters~~~~~~~*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	
	
	public PircBotX getPircBot() {
		return myBot;
	}
	
	public String getChannel() {
		return channel;
	}
}
