package view;

import gameObjects.Building;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.awt.Canvas;
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.Model;
import model.manager.Kappa;

public class View extends BasicGame {

	Thread t;

	String currMsg;
	int board[][];
	Image groundImg[][];


	Model myModel;
	// JFrame myFrame;
	// JPanel myPanel;

	List<Kappa> kappaList;
	List<Building> buildingList;

	// graphics
	ArrayList<Image> buildings;
	ArrayList<Image> ground;
	Image wallLeftRight;
	Image wallUpDown;
	Image wallWatchtower;
	Image wallGate;
	Image wallUpperLeftCorner;
	Image wallUpperRightCorner;
	Image wallLowerLeftCorner;
	Image wallLowerRightCorner;
	Image redTank;
	Image greenTank;

	Random rand;
	public View(Model myModel) {
		super("Drunken Democracy");
		this.myModel = myModel;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GameContainer arg0, org.newdawn.slick.Graphics arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		arg1.drawString(currMsg, 100, 100);
		drawGround();
		buildings.get(0).draw(100, 100);
		buildings.get(1).draw(200, 100);
		buildings.get(2).draw(300, 100);
		buildings.get(3).draw(400, 100);

		wallLeftRight.draw(100, 300);
		wallLeftRight.draw(116, 300);
		wallLeftRight.draw(132, 300);

		wallUpperRightCorner.draw(148, 300);

		wallUpDown.draw(148, 328);
		wallUpDown.draw(148, 346);
		wallUpDown.draw(148, 364);

		wallLowerRightCorner.draw(148, 382);

		wallLeftRight.draw(132, 382);
		wallLeftRight.draw(116, 382);
		wallLeftRight.draw(100, 382);

		wallLowerLeftCorner.draw(84, 382);

		wallUpDown.draw(84, 328);
		wallUpDown.draw(84, 346);
		wallUpDown.draw(84, 364);

		wallUpperLeftCorner.draw(84, 300);

		redTank.draw(200, 200);
		greenTank.draw(100, 200);
	}

	/*
<<<<<<< HEAD
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
				
=======
*/

	private void drawGround() {
		// TODO Auto-generated method stub
		for (int i = 0; i < groundImg.length; i++) {
			for (int j = 0; j < groundImg[0].length; j++) {
				Image ground = groundImg[i][j];
				ground.draw(ground.getWidth() * i, ground.getHeight() * j);
			}
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		board = new int[arg0.getWidth() / 48][arg0.getHeight() / 48];
		groundImg = new Image[arg0.getWidth() / 32 + 1][arg0.getHeight() / 34 + 1];
		currMsg = "";

		Image sprites = new Image("graphics/colony_0.png");
		ground = new ArrayList<Image>(2);
		ground.add(sprites.getSubImage(144, 8, 32, 34));
		ground.add(sprites.getSubImage(144, 38, 32, 34));
		rand = new Random();
		initGround();

		buildings = new ArrayList<Image>(10);
		// building pad
		buildings.add(sprites.getSubImage(272, 88, 48, 48));
		// factory
		buildings.add(sprites.getSubImage(328, 40, 40, 64));
		// silo
		buildings.add(sprites.getSubImage(376, 56, 32, 48));
		// clinic?
		buildings.add(sprites.getSubImage(104, 178, 32, 38));

		wallLeftRight = sprites.getSubImage(160, 262, 16, 26);
		wallUpDown = sprites.getSubImage(144, 200, 16, 18);
		wallWatchtower = sprites.getSubImage(176, 256, 16, 32);
		wallGate = sprites.getSubImage(160, 292, 32, 28);
		wallUpperLeftCorner = sprites.getSubImage(144, 262, 16, 28);
		wallUpperRightCorner = sprites.getSubImage(192, 262, 16, 28);
		/*
		 * may want to subtract 4 pixels from start y coordinate, increase
		 * height by 2
		 */
		wallLowerLeftCorner = sprites.getSubImage(144, 294, 16, 28);
		wallLowerRightCorner = sprites.getSubImage(192, 294, 16, 28);

		redTank = new Image("graphics/redTank.png");
		greenTank = new Image("graphics/greenTank.png");

	}

	private void initGround() {
		// TODO Auto-generated method stub
		for (int i = 0; i < groundImg.length; i++) {
			for (int j = 0; j < groundImg[0].length; j++) {
				int r = rand.nextInt(2);
				groundImg[i][j] = ground.get(r);
			}
		}
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
//		builtList = this.myGame.getBuilt();
	}

}
