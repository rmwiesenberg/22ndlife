package entities.subject;

import entities.item.Inventory;

public abstract class AbsSubject implements ISubject{
	// <x, y, z>
	private String name;
	private float[] position;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	
	public AbsSubject(String name, float[] position, Inventory inventory, 
			PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = inventory;
	}
	
	public AbsSubject(String name, float[] position, int slots, 
			PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = new Inventory(slots);
	}
	
	// Getters and Setters
	@Override
	public String getName() {
		return name;
	}
	
	
	@Override
	public float[] getPosition() {
		return position;
	}
	
	@Override
	public Inventory getInventory() {
		return inventory;
	}
	
	@Override
	public PhysicalHealth getPhysicalHealth() {
		return physicalHealth;
	}
}
