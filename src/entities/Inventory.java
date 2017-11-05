package entities;

import entities.exceptions.*;

public class Inventory {
	int slots;
	IItemStack[] items;
	
	public Inventory(int slots) {
		this.items = new MTItemStack[slots];
	}
	
	public Inventory(int slots, IItemStack[] items) 
			throws NotEnoughSpaceException {
		this.items = new MTItemStack[slots];
		if(items.length > this.items.length) {
			throw new NotEnoughSpaceException();
		} else {
			for (int i=0; i < items.length; i++) {
				this.items[i] = items[i];
			}
		}
	}
	
	public void addItem(ItemStack stack) 
			throws ItemStackException {
		// find a place to put the item
		int space = items.length;
		for(int i = 0; i < items.length; i++) {
			if (items[i].getCurSize() == 0 
					&& i == items.length) {
				space = i;
			} else if(items[i].getID() == stack.getID()) {
				space = i;
				break;
			} 
		}
		// try to add item
		if(space == items.length) {
			throw new NotEnoughSpaceException();
		} else {
			items[space] = items[space].addStack(stack);
		}
	}
	
	public void removeItem(ItemStack stack) 
			throws ItemStackException {
		// find item
		int space = items.length;
		for(int i = 0; i < items.length; i++) {
			if(items[i].getID() == stack.getID()) {
				space = i;
				break;
			}
		}
		// try to remove items
		if(space == items.length) {
			throw new DoesNotContainException();
		} else {
			items[space] = items[space].removeStack(stack);
		}
		// check if 0 items are left
		if(items[space].getCurSize() == 0) {
			items[space] = null;
		}
	}
	
	// Getters and Setters
	
}
