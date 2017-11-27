package entities.block;

import java.util.HashMap;

import org.joml.Vector3f;

import entities.item.ItemDrop;

public class SceneryBlock extends AbsBlock {
	SceneryObject[] scenery;
	Vector3f[] offset;
	Vector3f[] rotation;
	
	
	
	public SceneryBlock(int id, ItemDrop drop, SceneryObject[] scenery, Vector3f[] offset, Vector3f[] rotation) {
		super(id, drop);
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
