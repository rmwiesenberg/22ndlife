package entities;

import entities.exceptions.*;

public class Inventory {
	int slots;
	ItemStack[] items;
	
	public Inventory(int slots) {
		this.items = new ItemStack[slots];
	}
	
	public Inventory(int slots, ItemStack[] items) 
			throws NotEnoughSpaceException {
		this.items = new ItemStack[slots];
		if(items.length > this.items.length) {
			throw new NotEnoughSpaceException();
		} else {
			for (int i=0; i < items.length; i++) {
				this.items[i] = items[i];
			}
		}
	}
	
	public void addItem(Item item, int num) 
			throws NotEnoughSpaceException{
		// find a place to put the item
		int space = items.length;
		for(int i = 0; i < items.length; i++) {
			if (items[i] == null && i == items.length) {
				space = i;
			} else if(items[i].getID() == num) {
				space = i;
				break;
			} 
		}
		// try to add item
		if(space == items.length) {
			throw new NotEnoughSpaceException();
		} else if (items[space] == null){
			items[space] = new ItemStack(item, num);
		}
	}
	
	public void removeItem(Item item, int num) 
			throws NotEnoughItemsException, 
			DoesNotContainException{
		// find item
		int space = items.length;
		for(int i = 0; i < items.length; i++) {
			if(items[i].getID() == num) {
				space = i;
				break;
			}
		}
		// try to remove items
		if(space == items.length) {
			throw new DoesNotContainException();
		} else {
			items[space].remove(num);
		}
	}
	
	// Getters and Setters
	
}
