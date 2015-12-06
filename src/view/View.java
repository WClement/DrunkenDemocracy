package view;

import gameObjects.Building;

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

import model.Model;
import model.manager.Kappa;

public class View implements Runnable {

	Thread t;
	String threadName = "viewThread";

	Model myModel;
	JFrame myFrame;
	JPanel myPanel;
	
	List<Kappa> kappaList;
	List<Building> buildingList;

	public View(Model myGame) {
		this.myModel = myGame;
	}

	public void init() {
		kappaList = new ArrayList<Kappa>();
		buildingList = new ArrayList<Building>();
		myFrame = new JFrame();
		myFrame.setSize(600, 600);
		myPanel = new MyPanel(myModel);
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
		if (!kappaList.equals(myModel.getKappas())) {
			kappaList = myModel.getKappas();
		}
		if (!buildingList.equals(myModel.getBuildingsForDrawing())) {
			buildingList = myModel.getBuildingsForDrawing();
		}
		
		myPanel.repaint();
	}

	class MyPanel extends JPanel {
		Model myKingdom;

		public MyPanel(Model myModel) {
			this.myKingdom = myModel;
			setBackground(Color.GRAY);
		}

		File kappaFile = new File("Images/Kappa.png");
		File buildingFile = new File("Images/ShibeZ.png");

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (kappaList != null) {
				

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
			if (buildingList != null && buildingList.size() > 0) {
				for (int i = 0; i < buildingList.size(); i++) {
					try {
						g.drawImage(ImageIO.read(buildingFile), buildingList.get(i).getLocation().x,
								buildingList.get(i).getLocation().y, 50, 50, null);
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
