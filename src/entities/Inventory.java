package entities;

import exceptions.NotEnoughSpaceException;

public class Inventory {
	int slots;
	ItemStack[] items;
	
	public Inventory(int slots) {
		this.items = new ItemStack[slots];
	}
	
	public Inventory(int slots, ItemStack[] items) throws NotEnoughSpaceException {
		this.items = new ItemStack[slots];
		if(items.length > this.items.length) {
			throw new NotEnoughSpaceException();
		} else {
			for (int i=0; i < items.length; i++) {
				this.items[i] = items[i];
			}
		}
	}
	
	public void addItem(Item item) {
		
	}
	
	// Getters and Setters
	
}
