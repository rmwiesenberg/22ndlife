package boundary.gameData;

import boundary.renderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager window = new DisplayManager();
		
		
		window.init();
		
		window.loop();	
				
		window.terminate();
		
		
		
		

	}

	

}
