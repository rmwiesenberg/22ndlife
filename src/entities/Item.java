package entities;

public class Item implements IItem{
	private int id;
	private String name;
	private int maxSize;
	
	public Item(int id, String name, int maxSize) {
		this.id = id;
		this.name = name;
		this.maxSize = maxSize;
	}
	
	// Getters and Setters
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
}
