package entities.block;

import entities.item.ItemStack;

public class SceneryBlock extends AbsBlock {
	public int[][][] voxelArray;
	
	
	public SceneryBlock(int id, ItemStack[] items, float transparency) {
		super(id, items, transparency);
		this.voxelArray = new int[32][32][32];
	}
	
	// Getters and Setters
	public int[][][] getVoxelArray(){
		return voxelArray;
	}
	
	public int getVoxel(int x, int y, int z) {
		return voxelArray[x][y][z];
	}
	
	public SceneryBlock setVoxel(int x, int y, int z, int voxel) {
		voxelArray[x][y][z] = voxel;
		return this;
	}
	
	@Override
	public boolean isScenery() {
		return true;
	}
	
}
