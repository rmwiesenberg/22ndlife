package entities;

public abstract class absSubject implements ISubject{
	// <x, y, z>
	private float[] position;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	private MentalHealth mentalHealth;
	
	public absSubject(float[] position, Inventory inventory, 
			PhysicalHealth physHealth, MentalHealth mentHealth) {
		this.position = position;
		this.physicalHealth = physHealth;
		this.mentalHealth = mentHealth;
		this.inventory = inventory;
	}
	
	public absSubject(float[] position, int slots, 
			PhysicalHealth physHealth, MentalHealth mentHealth) {
		this.position = position;
		this.physicalHealth = physHealth;
		this.mentalHealth = mentHealth;
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
	
	@Override
	public MentalHealth getMentalHealth() {
		return mentalHealth;
	}
}
