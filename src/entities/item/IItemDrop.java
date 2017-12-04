package entities.item;

public interface IItemDrop {
	public IItemStack[] generate();
	public IItemStack[] generate(float modifier);
	
	// Getters and Setters
	public IItem[] getItems();
	public int[] getMin();
	public int[] getMax();
	public float[] getChance();	
}
