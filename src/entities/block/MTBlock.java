package entities.block;

import entities.item.IItemDrop;
import entities.item.MTItemDrop;

public class MTBlock implements IBlock, IWorldObject {

	@Override
	public boolean isScenery() {
		return false;
	}

    @Override
    public boolean isMT() {
        return true;
    }

    @Override
	public int getID() {
		return 0;
	}

	@Override
	public String getName() {
		return "empty";
	}

	@Override
	public IItemDrop getDrop() {
		return new MTItemDrop();
	}

}
