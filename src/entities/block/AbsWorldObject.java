package entities.block;

import entities.item.IItemDrop;

public abstract class AbsWorldObject implements IWorldObject {
	private int id;
	private String name;
	private IItemDrop drop;
	
	public AbsWorldObject(int id, String name, IItemDrop drop) {
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

	public IItemDrop getDrop() {
		return drop;
	}
}
