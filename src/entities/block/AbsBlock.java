package entities.block;

import entities.item.ItemStack;

public abstract class AbsBlock implements IBlock {
	public int id;
	public ItemStack[] items;
	public float transparency;
	
	public AbsBlock(int id, ItemStack[] items, float transparency) {
		this.transparency = transparency; 
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public ItemStack[] getItems() {
		return items;
	}
}
