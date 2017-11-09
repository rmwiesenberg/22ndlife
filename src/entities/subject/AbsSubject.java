package entities.subject;

import entities.item.Inventory;
import math.Vec3f;

public abstract class AbsSubject implements ISubject{
	// <x, y, z>
	private String name;
	private Vec3f position;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	
	public AbsSubject(String name, Vec3f position, Inventory inventory, 
			PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = inventory;
	}
	
	public AbsSubject(String name, Vec3f position, int slots, 
			PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = new Inventory(slots);
	}
	
	public ISubject translate(Vec3f delta) {
		return null;
		
	}
	
	public ISubject rotate(Vec3f delta) {
		return null;
	}
	
	// Getters and Setters
	@Override
	public String getName() {
		return name;
	}
	
	
	@Override
	public Vec3f getPosition() {
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
