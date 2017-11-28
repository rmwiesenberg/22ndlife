package entities.block;

import entities.item.ItemDrop;

public abstract class AbsWorldObject implements IWorldObject {
	private int id;
	private String name;
	private ItemDrop drop;
	
	public AbsWorldObject(int id, String name, ItemDrop drop) {
		this.id = id;
		this.name = name;
		this.drop = drop;
	}

	
	// Getters and Setters
	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ItemDrop getDrop() {
		return drop;
	}
}
