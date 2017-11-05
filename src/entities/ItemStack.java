package entities;

import entities.exceptions.NotEnoughSpaceException;

public class ItemStack {
	private int curSize;
	private int maxSize;
	private Item item;
	
	public ItemStack(int curSize, int maxSize, Item item) {
		this.curSize = curSize;
		this.maxSize = maxSize;
		this.item = item;
	}
	
	public void add(int num) throws NotEnoughSpaceException{
		throw new NotEnoughSpaceException();
	}
	
	public void remove(int num) {
		
	}
	
	// Getters and Setters
	public int getCurSize() {
		return curSize;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getItemId() {
		return item.getID();
	}
}
