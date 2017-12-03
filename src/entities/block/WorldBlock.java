package entities.block;

import entities.item.ItemStack;

public class WorldBlock extends AbsBlock {
	int voxel;
	int height; // 0 to 100
	
	public WorldBlock(int id, ItemStack[] items, int voxel) {
		super(id, items, 1);
		
		this.voxel = voxel;
		this.height = 0;
	}
	
	public WorldBlock(int id, ItemStack[] items, int voxel, int height) {
		super(id, items, 1);
		
		this.voxel = voxel;
		this.height = height;
	}
	
	public WorldBlock(int id, ItemStack[] items, int voxel, float transparency) {
		super(id, items, transparency);
		
		this.voxel = voxel;
		this.height = 0;
	}
	
	public WorldBlock(int id, ItemStack[] items, int voxel, float transparency, int height) {
		super(id, items, transparency);
		
		this.voxel = voxel;
		this.height = height;
	}
	
	public int getVoxel() {
		return voxel;
	}
	
	@Override
	public boolean isScenery() {
		return false;
	}
}
