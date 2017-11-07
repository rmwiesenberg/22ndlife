package entities;

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
	
	// TODO: COMBINATION
}
