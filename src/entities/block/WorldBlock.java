package entities.block;

import entities.item.ItemDrop;
import entities.item.MTItemDrop;

public class WorldBlock extends AbsWorldObject implements IBlock {
	int voxel;
	
	public WorldBlock(int id, String name, ItemDrop drop, int voxel) {
		super(id, name, drop);		
		this.voxel = voxel;
	}
	
	public WorldBlock(int id, String name, int voxel) {
		super(id, name, new MTItemDrop());		
		this.voxel = voxel;
	}
	
	public int getVoxel() {
		return voxel;
	}

	@Override
	public boolean isScenery() {
		return false;
	}
}
