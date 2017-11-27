package entities.block;

import entities.item.ItemDrop;

public class WorldBlock extends AbsBlock {
	int voxel;
	
	public WorldBlock(int id, ItemDrop drop, int voxel) {
		super(id, drop);		
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
