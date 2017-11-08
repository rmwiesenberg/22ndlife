package entities;

import entities.exceptions.*;

public class Recipe {
	private IItemStack[] input;
	private IItemStack[] output;
	
	public Recipe(IItemStack[] input, IItemStack[] output) {
		this.input = input;
		this.output = output;
	}
	
	public IItemStack[] getInput() {
		return input;
	}
	
	public IItemStack[] getOutput() {
		return output;
	}
	
	public Inventory doRecipie(Inventory inv, int num) 
			throws NotEnoughItemsException {
		for(int i = 0; i < input.length; i++) {
			if(! inv.contains(input[i])) {
				throw new NotEnoughItemsException();
			}
		for(int i = 0; i < input.length; i++) {
			if(! inv.contains(output[i])) {
				throw new NotEnoughItemsException();
			}
		}
		}
	}
}
