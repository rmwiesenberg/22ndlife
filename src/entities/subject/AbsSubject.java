package entities.subject;

import entities.item.Inventory;
import math.Vec3f;

public abstract class AbsSubject implements ISubject{
	// <x, y, z>
	private String name;
	private Vec3f position;
	private Vec3f orientation;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	
	public AbsSubject(String name, Vec3f position, Vec3f orientation, 
			Inventory inventory, PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.orientation = orientation;
		this.physicalHealth = physHealth;
		this.inventory = inventory;
	}
	
	public AbsSubject(String name, Vec3f position, Vec3f orientation, 
			int slots, PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.orientation = orientation;
		this.physicalHealth = physHealth;
		this.inventory = new Inventory(slots);
	}
	
	public ISubject translate(Vec3f delta) {
		position = position.add(delta); 
		return this;
		
	}
	
	public ISubject rotate(float angle, Vec3f axis) {
		orientation = orientation.rotate(angle, axis);
		return this;
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
	public Vec3f getOrientation() {
		return orientation;
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
