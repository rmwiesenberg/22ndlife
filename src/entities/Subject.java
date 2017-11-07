package entities;

public class Subject {
	// <x, y, z>
	private float[] position;
	private Inventory inventory;
	private AbsHealth health;
	private float healthInput;
	
	public Subject(float[] position, Inventory inventory, float healthInput) {
		this.position = position;
		this.health.maxHealth = this.healthInput;
		this.health.currentHealth = this.health.maxHealth;
		this.inventory = inventory;
	}
	
	public Subject(float[] position, int slots, float healthInput) {
		this.position = position;
		this.health.maxHealth = this.healthInput;
		this.health.currentHealth = this.health.maxHealth;
		this.inventory = new Inventory(slots);
	}
}
