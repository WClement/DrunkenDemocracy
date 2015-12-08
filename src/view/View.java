package view;

import gameObjects.Building;
import gameObjects.Kingdom;
import gameObjects.LocationNode;
import gameObjects.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import javax.imageio.ImageIO;
//import javax.swing.JFrame;
//import javax.swing.JPanel;


import org.newdawn.slick.BasicGame;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import model.Model;

public class View extends BasicGame {
	
	final int KINGDOM_WIDTH = 16*16;
	final int KINGDOM_HEIGHT = 18*20;

	String currMsg;
	int board[][];
	Image groundImg[][];


	Model myModel;
	// JFrame myFrame;
	// JPanel myPanel;

	List<Building> modelBuildings;
	
	List<Kingdom> kingdoms;

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
	
	ArrayList<ViewLocation> locs;
	ArrayList<ViewTrack> tracks;
	
	Random rand;
	public View(Model myModel) {
		super("Drunken Democracy");
		this.myModel = myModel;
		locs = new ArrayList<ViewLocation>();
		kingdoms = new ArrayList<Kingdom>();
	}
	
	@Override
	public void render(GameContainer arg0, Graphics arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		Font font = new Font("Verdana", Font.BOLD, 32);
		TrueTypeFont ttf = new TrueTypeFont(font, true);
//		arg1.drawString(currMsg, 100, 100);
		String gold = "" + myModel.kingdomManager.getKingdomGold(0);
		
		drawGround();
		for(int i = 0; i < locs.size(); i++){
			ViewLocation loc = locs.get(i);
			loc.draw();
			ttf.drawString(loc.startX - 10, loc.height + 25, i + "");
		}
		
		ttf.drawString(10, 10, "Kingdom 1: " + gold);
	}

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

		// walls
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
		
		Image[] walls = {wallLeftRight, wallUpDown, wallUpperLeftCorner, wallUpperRightCorner,
				wallLowerLeftCorner, wallLowerRightCorner};
		
		ArrayList<LocationNode> loc = myModel.mapManager.getLocations();
		
		/* real janky shit */
		ArrayList<Integer> coords = new ArrayList<Integer>();
		coords.add(100);
		coords.add(100);
		
		coords.add(800);
		coords.add(100);
		
		coords.add(100);
		coords.add(400);
		
		coords.add(800);
		coords.add(400);
		
		for(int i = 0; i < loc.size(); i++){
			LocationNode node = loc.get(i);
			locs.add(new ViewLocation(coords.get(2*i), coords.get(2*i+1), walls, node.getBuildingSlots(), buildings.get(0), node));
		}

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
	public synchronized void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		modelBuildings = this.myModel.getBuildingsForDrawing();
		for(int i = 0; i < locs.size(); i++){
			ViewLocation loc = locs.get(i);
			loc.updateBuildings(modelBuildings, this.buildings);
		}
	}
}
