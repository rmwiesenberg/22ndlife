package game;

import boundary.DisplayManager;
import controllers.MainStateController;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager displayManager = new DisplayManager();
		displayManager.init();

		MainStateController mainStateController = new MainStateController(displayManager);
		mainStateController.init();

		while (!displayManager.shouldWindowClose()){
			mainStateController.render();
		}

		mainStateController.terminate();
		displayManager.terminate();
	}
}
