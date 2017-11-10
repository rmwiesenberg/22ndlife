package entities.subject;

import entities.item.Inventory;
import math.*;
import math.vectors.Orientation;
import math.vectors.Position;
import math.vectors.Quaterneon;

public abstract class AbsSubject implements ISubject{
	// <x, y, z>
	private String name;
	private Pose pose;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	
	public AbsSubject(String name, Pose pose, Inventory inventory, PhysicalHealth physHealth) {
		this.name = name;
		this.pose = pose;
		this.physicalHealth = physHealth;
		this.inventory = inventory;
	}
	
	public AbsSubject(String name, Pose pose, int slots, PhysicalHealth physHealth) {
		this.name = name;
		this.pose = pose;
		this.physicalHealth = physHealth;
		this.inventory = new Inventory(slots);
	}
	
	public ISubject translate(Position delta) {
		pose = pose.translate(delta);
		return this;
		
	}
	
	public ISubject rotate(Quaterneon quat) {
		pose = pose.rotate(quat);
		return this;
	}
	
	// Getters and Setters
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Pose getPose() {
		return pose;
	}
	
	@Override
	public Position getPosition() {
		return pose.getPosition();
	}
	
	@Override
	public Orientation getOrientation() {
		return pose.getOrientation();
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
