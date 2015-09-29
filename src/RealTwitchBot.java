import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class RealTwitchBot extends ListenerAdapter {

	Queue<String> masterQueue = new LinkedList();

	Timer time;
	JFrame theFrame;
	ImageCanvas theCanvas;
	Graphics g;
	boolean kappaFlag;

	public void onGenericMessage(GenericMessageEvent event) {
		String curr = event.getMessage();
		// if (event.getMessage().startsWith("?repeat"))
		masterQueue.add(event.getMessage());
		// System.out.println(event.getMessage());

	}

	private void printNextInQueue() {
		if (masterQueue.peek() != null) {

			if (masterQueue.peek().contains("Kappa"))
				kappaFlag = true;
			else
				kappaFlag = false;

			theCanvas.setKappaFlag(kappaFlag);
			System.out.println(masterQueue.remove());
		}
	}

	private class MyClock extends TimerTask {
		public void run() {
			printNextInQueue();
			theCanvas.repaint();
		}
	}

	
	public class ImageCanvas extends Canvas {

		private BufferedImage img;
		private boolean kappaFlag;

		public ImageCanvas() {
			try {
				img = ImageIO.read(new File("Images/Kappa.png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		public void setKappaFlag(boolean kappaFlag) {
			this.kappaFlag = kappaFlag;
		}

		@Override
		public Dimension getPreferredSize() {
			return img == null ? new Dimension(200, 200) : new Dimension(
					img.getWidth(), img.getHeight());
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (img != null && kappaFlag) {
				int x = (getWidth() - img.getWidth()) / 2;
				int y = (getHeight() - img.getHeight()) / 2;
				g.drawImage(img, 10, 10, 200, 200, this);
			}

		}

	}

	private void startGui() {
		theFrame = new JFrame();
		theCanvas = new ImageCanvas();
		theFrame.setSize(500, 500);
		theFrame.show();
		theFrame.add(theCanvas);
	}

	public RealTwitchBot() {
		startGui();
		time = new Timer();
		time.schedule(new MyClock(), 0, 3000);

		kappaFlag = false;

	}

	public static void main(String[] args) {

		RealTwitchBot theBot = new RealTwitchBot();

		Properties properties = new Properties();
		InputStream in = null;
		String user = "";
		String pass = "";
		String url = "";
		String port = "";
		String channel = "";
		boolean nickChange;
		try {

			in = new FileInputStream("config.properties");

			properties.load(in);

			user = properties.getProperty("user");
			pass = properties.getProperty("pass");
			url = properties.getProperty("hostname");
			port = properties.getProperty("port");
			channel = properties.getProperty("channel");
			nickChange = Boolean.parseBoolean(properties
					.getProperty("autoNickChange"));

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
		Configuration configuration = new Configuration.Builder()
				.setName(user)
				// Set the nick of the bot. CHANGE IN YOUR CODE
				.setServerHostname(url)
				// Join the freenode network
				.setServerPassword(pass).setAutoNickChange(true)
				.setServerPort(Integer.parseInt(port)).addListener(theBot)
				.addAutoJoinChannel("#" + channel).buildConfiguration();

		PircBotX myBot = new PircBotX(configuration);
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

}
