package entities;

import entities.exceptions.*;

public class ItemStack {
	private int curSize;
	private Item item;
	
	public ItemStack(Item item, int curSize) 
			throws NotEnoughSpaceException {
		this.item = item;
		this.curSize = 0;
		add(curSize);
	}
	
	public void add(int num) 
			throws NotEnoughSpaceException{
		if(num > (getMaxSize() - curSize)) {
			throw new NotEnoughSpaceException();
		} else {
			curSize += num;
		}
	}
	
	public void remove(int num) 
			throws NotEnoughItemsException {
		if(curSize < num) {
			throw new NotEnoughItemsException();
		} else {
			curSize -= num;
		}
	}
	
	// Getters and Setters
	public int getCurSize() {
		return curSize;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getID() {
		return item.getID();
	}
	
	public String getName() {
		return item.getName();
	}
	
	public int getMaxSize() {
		return item.getMaxSize();
	}
}
