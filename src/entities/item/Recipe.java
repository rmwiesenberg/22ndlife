package entities.item;

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
	
	public Inventory doRecipie(Inventory inv) 
			throws ItemStackException {
		int i;
		for(i = 0; i < input.length; i++) {
			inv.removeItem(input[i]);
		}
		for(i = 0; i < output.length; i++) {
			inv.addItem(output[i]);
		}
		return inv;
	}
}
