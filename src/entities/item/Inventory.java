package entities.item;

import java.util.ArrayList;

import entities.exceptions.*;

public class Inventory {
	int slots;
	IItemStack[] items;
	
	public Inventory(int slots) {
		this.items = new IItemStack[slots];
		for(int i=0; i < this.items.length; i++) {
			this.items[i] = new MTItemStack();
		}
	}
	
	public Inventory(int slots, IItemStack[] items) 
			throws NotEnoughSpaceException {
		this(slots);
		if(items.length > this.items.length) {
			throw new NotEnoughSpaceException();
		} else {
			for (int i=0; i < items.length; i++) {
				this.items[i] = items[i];
			}
		}
	}
	
	public void addItem(IItemStack stack) 
			throws ItemStackException {
		// find a place to put the item
		int space = items.length;
		for(int i = 0; i < items.length; i++) {
			if (items[i].getCurSize() == 0 
					&& space == items.length) {
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
	
	public void removeItem(IItemStack stack) 
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
	}
	
	public boolean contains(IItemStack stack) {
		int cont_num = 0;
		for (int i = 0; i < items.length; i++) {
			if(items[i].getID() == stack.getID()) {
				cont_num += items[i].getCurSize();
			}
		}
		if(cont_num <= stack.getCurSize()) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getItemNum(Item item) {
		int cont_num = 0;
		for (int i = 0; i < items.length; i++) {
			if(items[i].getID() == item.getID()) {
				cont_num += items[i].getCurSize();
			}
		}
		return cont_num;
	}
	
	public ArrayList<Integer> getMTItems() {
		ArrayList<Integer> posns = new ArrayList<Integer>();
		for (int i=0; i < items.length; i++) {
			if(items[i].getID() == 0) {
				posns.add(i);
			}
		}
		return posns;
	}

	// Getters and Setters
	public IItemStack[] getItems() {
		return items;
	}
	
	public IItemStack getItem(int pos) {
		return items[pos];
	}
	
	
	
}
