package model;

public class TimedExecutor {
	
	Game myGame;
	
	public TimedExecutor(Game myGame) {
		this.myGame = myGame;
	}

	public void gameLoop() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	
		while (true) {
			
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double)OPTIMAL_TIME);
			
			doGameUpdates(delta);
			
			//render();
			
			
			try {
				long temp = (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000;
				if (temp < 0)
					temp = 0;
				Thread.sleep(temp);
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	int buildCounter = 600;
	
	private void doGameUpdates(double delta) {
//		System.out.println(delta);
		
		myGame.manageManagers();
		
		if (buildCounter >= 600) {
			if (myGame.executeBuildAction())
				buildCounter = 0;
		}
		else {
			buildCounter++;
		}
		
	}
}
