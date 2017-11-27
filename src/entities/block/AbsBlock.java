package entities.block;

import entities.item.ItemDrop;

public abstract class AbsBlock implements IBlock {
	private int id;
	private ItemDrop drop;
	
	public AbsBlock(int id, ItemDrop drop) {
		this.id = id;
		this.drop = drop;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public ItemDrop getDrop() {
		return drop;
	}
}
