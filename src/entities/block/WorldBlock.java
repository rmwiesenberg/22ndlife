package entities.block;

import entities.Voxel;
import entities.item.ItemDrop;
import entities.item.MTItemDrop;

public class WorldBlock extends AbsWorldObject implements IBlock {
	Voxel voxel;
	
	public WorldBlock(int id, String name, ItemDrop drop, Voxel voxel) {
		super(id, name, drop);		
		this.voxel = voxel;
	}
	
	public WorldBlock(int id, String name, Voxel voxel) {
		super(id, name, new MTItemDrop());		
		this.voxel = voxel;
	}
	
	public Voxel getVoxel() {
		return voxel;
	}

	@Override
	public boolean isScenery() {
		return false;
	}

    @Override
    public boolean isMT() {
        return true;
    }
}
