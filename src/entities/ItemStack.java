package entities;

import entities.exceptions.*;

public class ItemStack implements IItemStack{
	private int curSize;
	private Item item;
	
	public ItemStack(Item item, int curSize) 
			throws NotEnoughSpaceException {
		this.item = item;
		this.curSize = 0;
		add(curSize);
	}
	
	@Override
	public IItemStack addStack(ItemStack stack) 
			throws ItemStackException {
		if(stack.getID() != getID()) {
			throw new DoesNotContainException();
		}
		add(stack.getCurSize());
		return this;
		
	}
	
	private void add(int num) 
			throws NotEnoughSpaceException {
		if(num > (getMaxSize() - getCurSize())) {
			throw new NotEnoughSpaceException();
		} else {
			curSize += num;
		}
	}
	
	@Override
	public IItemStack removeStack(ItemStack stack) 
			throws ItemStackException {
		if(stack.getID() != getID()) {
			throw new DoesNotContainException();
		}		
		remove(stack.getCurSize());
		if(getCurSize() == 0) {
			return new MTItemStack();
		} else {
			return this;
		}
	}
	
	private void remove(int num) 
			throws NotEnoughItemsException {
		if(curSize < num) {
			throw new NotEnoughItemsException();
		} else {
			curSize -= num;
		}
	}
	
	// Getters and Setters
	@Override
	public int getCurSize() {
		return curSize;
	}
	
	@Override
	public IItem getItem() {
		return item;
	}
	
	@Override
	public int getID() {
		return item.getID();
	}
	
	@Override
	public String getName() {
		return item.getName();
	}
	
	@Override
	public int getMaxSize() {
		return item.getMaxSize();
	}
}
