package entities;

import entities.exceptions.*;

public class MTItemStack implements IItemStack {
	private MTItem item = new MTItem();

	public MTItemStack() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public IItemStack addStack(IItemStack stack) 
			throws NotEnoughSpaceException{
		return stack;
	}

	@Override
	public IItemStack removeStack(IItemStack stack) 
			throws NotEnoughItemsException{
		// has no items so how it could remove
		throw new NotEnoughItemsException();
	}

	@Override
	public int getCurSize() {
		return 0;
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
