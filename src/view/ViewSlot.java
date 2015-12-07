package view;

import org.newdawn.slick.Image;

public class ViewSlot implements Drawable{
	
	// coordinates for where to draw the slot
	int x, y;
	Image slotImage;
	ViewBuilding building;
	int width, height;
	
	public ViewSlot(int x, int y, Image slotImage){
		this.x = x;
		this.y = y;
		this.slotImage = slotImage;
		this.width = slotImage.getWidth();
		this.height = slotImage.getHeight();
	}
	
	public void setBuilding(ViewBuilding bldg){
		this.building = bldg;
	}
	
	public void destroyBuilding(){
		if (this.building != null){
			/* TODO: add death animation */
			this.building = null;
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		slotImage.draw(x, y);
		// TODO: draw building in this slot if it isn't null
		if (this.building != null){
			this.building.draw();
		}
	 }
}
