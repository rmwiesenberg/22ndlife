package boundary.gameData;

import boundary.renderEngine.DisplayManager;
import boundary.renderEngine.Loader;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager window = new DisplayManager();
		
		
		window.init();
		
		window.loop();	
				
		window.terminate();
		
		
		
		

	}

	

}
