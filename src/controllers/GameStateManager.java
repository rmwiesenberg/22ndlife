package controllers;

import java.util.HashMap;

import org.joml.Vector3f;

import boundary.renderEngine.Frame;

public class GameStateManager {

	public GameStateManager() {
		
	}
	
	public HashMap<Integer, HashMap<Integer, Vector3f[]>> getCurrentView(){
		HashMap<Integer, HashMap<Integer, Vector3f[]>> rtn = 
				new HashMap<Integer, HashMap<Integer, Vector3f[]>>(); 
		HashMap<Integer, Vector3f[]> vox = new HashMap<Integer, Vector3f[]>();
		Vector3f[] vox_vec = new Vector3f[4*1];
		vox_vec[0] = new Vector3f(-.5f, .5f, 0);
		vox_vec[1] = new Vector3f(.5f, .5f, 0);
		vox_vec[2] = new Vector3f(.5f, -.5f, 0);
		vox_vec[3] = new Vector3f(-.5f, -.5f, 0);
		vox.put(0, vox_vec);
		rtn.put(4, vox);
		return rtn;
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

	public Frame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
