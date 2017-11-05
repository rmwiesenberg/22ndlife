package entities;

public class Subject {
	// <x, y, z>
	float[] position;
	float health;
	float maxHealth;
	Inventory inventory;
	
	public Subject(float[] position, Inventory inventory) {
		this.position = position;
		this.maxHealth = 20;
		this.health = this.maxHealth;
		this.inventory = inventory;
	}
	
	public Subject(float[] position, int slots) {
		this.position = position;
		this.maxHealth = 20;
		this.health = this.maxHealth;
		this.inventory = new Inventory(slots);
	}
}
