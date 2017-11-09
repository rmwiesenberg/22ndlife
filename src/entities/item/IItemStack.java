package entities.item;

import entities.exceptions.*;

public interface IItemStack {
	IItemStack addStack(IItemStack stack) 
			throws ItemStackException;
	IItemStack removeStack(IItemStack stack) 
			throws ItemStackException;
	int getCurSize();
	IItem getItem();
	int getID();
	String getName();
	int getMaxSize();
}
