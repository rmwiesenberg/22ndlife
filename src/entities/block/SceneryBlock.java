package entities.block;

import java.util.HashMap;

import org.joml.Vector3f;

public class SceneryBlock implements IBlock {
	SceneryObject[] scenery;
	Vector3f[] offset;
	Vector3f[] rotation;
	
	
	
	public SceneryBlock(SceneryObject[] scenery, Vector3f[] offset, Vector3f[] rotation) {
		this.scenery = scenery;
		this.offset = offset;
		this.rotation = rotation;
	}


	public HashMap<Integer, HashMap<Integer, Vector3f[]>> getVisible(Vector3f view){
		return null;		
	}
	
	@Override
	public boolean isScenery() {
		return true;
	}
}
