package game;

import java.util.HashMap;

import org.joml.Vector3f;

import boundary.renderEngine.DisplayManager;
import controllers.GameStateManager;
import controllers.parsers.VoxelParser;
import controllers.parsers.exceptions.InvalidImageSizeException;
import entities.Voxel;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager window = new DisplayManager();
		HashMap<Integer, Voxel> voxels = null;
		
		try {
			voxels = VoxelParser.readJSON("resources/json/voxel.json");
		} catch (InvalidImageSizeException e) {
			e.printStackTrace();
		}
		
		window.init(voxels);
		GameStateManager gsm = new GameStateManager();
		
		while (!window.shouldClose()) {
			gsm.update();
			window.loop(gsm.getFrame());
		}
		
				
		window.terminate();
		
		
		
		

	}

	

}
