package entities.block;

import entities.item.ItemStack;

public interface IBlock {
	int getID();
	public ItemStack[] getItems();
	boolean isScenery();
}
