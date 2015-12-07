package view;

import org.newdawn.slick.Image;

public class ViewBuilding {
	Image sprite;
	int x, y;
	
	public ViewBuilding(Image img, int x, int y){
		this.sprite = img;
		this.x = x;
		this.y = y;
	}
	
	public Image getSprite(){
		return this.sprite;
	}
}
