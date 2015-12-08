package view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class ViewThread implements Runnable {
	AppGameContainer app;
	Thread t;
	
	public ViewThread(View theView){
		try {
			app = new AppGameContainer(theView);
			app.setDisplayMode(1366, 768, false);
	        app.setShowFPS(false);
	        app.setAlwaysRender(true);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start(){
		if (t == null){
			t = new Thread(this, "theView");
			t.start();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}