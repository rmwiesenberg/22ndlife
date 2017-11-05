package entities;

import entities.exceptions.*;

public class ItemStack {
	private int curSize;
	private Item item;
	
	public ItemStack(Item item, int curSize) {
		this.curSize = curSize;
		this.item = item;
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
	
	public int getItemID() {
		return item.getID();
	}
	
	public String getItemName() {
		return item.getName();
	}
	
	public int getMaxSize() {
		return item.getMaxSize();
	}
}
