package entities.block;

import entities.item.ItemDrop;



public interface IBlock {	
	public int getID();
	public ItemDrop getDrop();
	public boolean isScenery();
}
