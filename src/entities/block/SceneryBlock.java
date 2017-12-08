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

	@Override
	public boolean isScenery() {
		return true;
	}

    @Override
    public boolean isMT() {
        return true;
    }
}
