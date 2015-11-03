package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Kingdom;
import model.manager.Kappa;

public class View implements Runnable {

	Thread t;
	String threadName = "viewThread";

	Kingdom myGame;
	JFrame myFrame;
	JPanel myPanel;
	
	List<Kappa> kappaList;

	public View(Kingdom myGame) {
		this.myGame = myGame;
	}

	public void init() {
		kappaList = new ArrayList<Kappa>();
		myFrame = new JFrame();
		myFrame.setSize(600, 600);
		myPanel = new MyPanel(myGame);
		myFrame.add(myPanel);
	}

	public void start() {
		System.out.println("Starting view");
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void update() {
		if (!kappaList.equals(myGame.getKappas())) {
			kappaList = myGame.getKappas();
		}
		myPanel.repaint();
	}

	class MyPanel extends JPanel {
		Kingdom myKingdom;

		public MyPanel(Kingdom myKingdom) {
			this.myKingdom = myKingdom;
			setBackground(Color.GRAY);
		}

		File kappaFile = new File("Images/Kappa.png");

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (kappaList != null) {
				
				/*
				try {
					for (Kappa k : kappaList) {
						try {
							g.drawImage(ImageIO.read(kappaFile), k.getX(),
									k.getY(), 50, 50, null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (ConcurrentModificationException c) {

				}
				*/
				for (int i = 0; i < kappaList.size(); i++) {
					try {
						g.drawImage(ImageIO.read(kappaFile), kappaList.get(i).getX(),
								kappaList.get(i).getY(), 50, 50, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				

			}

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		myFrame.setVisible(true);

	}

}
