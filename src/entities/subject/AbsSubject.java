package entities.subject;

import org.ejml.simple.SimpleMatrix;

import entities.item.Inventory;
import math.*;
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
	
	public ISubject translate(SimpleMatrix delta) {
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
