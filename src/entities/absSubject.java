package entities;

public abstract class absSubject implements ISubject{
	// <x, y, z>
	private float[] position;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	
	public absSubject(float[] position, Inventory inventory, 
			PhysicalHealth physHealth) {
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = inventory;
	}
	
	public absSubject(float[] position, int slots, 
			PhysicalHealth physHealth) {
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = new Inventory(slots);
	}
	
	// Getters and Setters
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
