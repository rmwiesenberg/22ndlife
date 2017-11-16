package game;

import java.util.HashMap;

import boundary.renderEngine.DisplayManager;
import controllers.handlers.VoxelHandler;
import controllers.handlers.excpetions.InvalidImageSizeException;
import entities.Voxel;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager window = new DisplayManager();
		
		try {
			HashMap<Integer, Voxel> voxels = VoxelHandler.readJSON("resources/json/voxel.json");
		} catch (InvalidImageSizeException e) {
			e.printStackTrace();
		}
		
		window.init();
		
		window.loop();	
				
		window.terminate();
		
		
		
		

	}

	

}
