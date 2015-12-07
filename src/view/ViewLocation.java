package view;

import gameObjects.Building;
import gameObjects.LocationNode;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;

public class ViewLocation implements Drawable{

	int startX, startY; // pixel location of upper-left corner of kingdom
	int width, height;  // pixel width and height of kingdom
						// should be a multiple of wall section width (16
						// pixels, currently)
						// and height (18 pixels, currently)
	LocationNode loc;

	Image wallLeftRight;
	Image wallUpDown;
	Image wallWatchtower;
	Image wallGate;
	Image wallUpperLeft;
	Image wallUpperRight;
	Image wallLowerLeft;
	Image wallLowerRight;
	Image slotImage;
	
	ArrayList<ViewSlot> slots;

	public ViewLocation(int startX, int startY, Image[] walls, int slots, Image slotImg,
			LocationNode loc) {
		this.startX = startX;
		this.startY = startY;
		this.width = startX + (4 * (slotImg.getWidth() + 10) + 11); // locations are all 4 slots wide for now
		this.height = (int) (startY + (Math.ceil((slots / 4.0)) * (slotImg.getHeight() + 10) + 10));
		wallLeftRight = walls[0];
		wallUpDown = walls[1];
		wallUpperLeft = walls[2];
		wallUpperRight = walls[3];
		wallLowerLeft = walls[4];
		wallLowerRight = walls[5];
		this.slotImage = slotImg;
		this.slots = new ArrayList<ViewSlot>(slots);
		this.loc = loc;
		setSlots(slots);
	}
	
	public void setSlots(int slots){
		int slotSep = 10; // number of pixels between slots
		int x = startX + slotSep;
		int y = startY + slotSep;
		int slotWidth  = slotImage.getWidth();
		int slotHeight = slotImage.getHeight();
		
		
		
		for(int i = 0; i < slots; i++){
			ViewSlot vs = new ViewSlot(x,y, slotImage);
			this.slots.add(vs);
//			vs.setBuilding(new ViewBuilding(slot.getBuilding()));
			x += slotWidth + slotSep;
			// check if next slot will overlap with location boundary
			if (x + slotWidth + slotSep >= this.width){
				x = startX + slotSep;
				y += slotHeight + slotSep;
			}
		}
	}

	public void draw() {
		drawWalls();
		drawBuildSlots();
	}

	private void drawBuildSlots() {
		// TODO Auto-generated method stub
		for(ViewSlot slot : slots){
			slot.draw();
		}
	}

	private void drawWalls() {
		// TODO Auto-generated method stub
		int wallHorizWidth  = wallLeftRight.getWidth();
		int wallHorizHeight = wallLeftRight.getHeight();
		int wallVertWidth  = wallUpDown.getWidth();
		int wallVertHeight = wallUpDown.getHeight();
		
		// draw left walls
		drawWallUpDown(startX - wallUpDown.getWidth(), startY);
		// draw right walls
		drawWallUpDown(width, startY);
		// draw top walls + corners
		drawWallLeftRight(startX - wallUpperLeft.getWidth(), startY - wallHorizHeight, wallUpperLeft, wallUpperRight);
		// draw bottom walls + corners
		drawWallLeftRight(startX - wallUpperLeft.getWidth(), height, wallLowerLeft, wallLowerRight);
	}

	private void drawWallUpDown(int x, int y) {
		// TODO Auto-generated method stub
		int wallHeight = wallUpDown.getHeight();
//		int height = this.height - wallLowerRight.getHeight() - 12;
		for (int i = y; i <= height; i += wallHeight) {
			wallUpDown.draw(x, i);
		}
	}

	private void drawWallLeftRight(int x, int y, Image leftCorner, Image rightCorner) {
		// TODO Auto-generated method stub
		int wallWidth = wallLeftRight.getWidth();
		int wallHeight = wallLeftRight.getHeight();
		int cornerWidth = rightCorner.getWidth();
		leftCorner.draw(x, y);
		for (int i = startX; i < width; i += wallWidth) {
			wallLeftRight.draw(i, y);
		}
		rightCorner.draw(width, y);
	}

	public void updateBuildings(List<Building> modelBuildings, List<Image> bldgImg) {
		// TODO Auto-generated method stub
		Image bldg;
		for (int i = 0; i < modelBuildings.size(); i++){
			Building building = modelBuildings.get(i);
			int index = building.getLocationIndex();
			ViewSlot slot = slots.get(index);
			
			bldg = getBuilding(building, bldgImg);
			
			// get midpoint of slot sprite
			int slotMidX = slot.x + (slot.width  / 2);
			int slotMidY = slot.y + (slot.height / 2);
			
			int bldgMidY = getBldgPlatform(building,bldgImg, slot);
			
			int x = slotMidX - (bldg.getWidth()  / 2);
			int y = (slot.y + slot.height) - (bldg.getHeight());
			slot.setBuilding(new ViewBuilding(bldg, x, y));
		}
	}
	
	// gets the upper left corner of the platform in the sprite of a building
	private int getBldgPlatform(Building bldg, List<Image> bldgImg, ViewSlot slot) {
		// TODO Auto-generated method stub
		
		switch(bldg.getName()){
		case "tower":
			return 74;
		}
		return slot.y;
	}

	private Image getBuilding(Building bldg, List<Image> buildings){
		
		switch(bldg.getName()){
		case "tower":
			return buildings.get(1);
		case "goldmine":
			return buildings.get(3);
		default:
			System.out.println("ERROR: Unrecognized building");
			return buildings.get(0);
		}
	}
}
