package entities.block;

import entities.item.IItemDrop;

public interface IWorldObject {
	public int getID();
	public String getName();
	public IItemDrop getDrop();
}
