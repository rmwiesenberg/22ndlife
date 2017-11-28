package entities.subject;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import entities.item.Inventory;
import entities.subject.health.PhysicalHealth;
import entities.world.Camera;

public abstract class AbsSubject implements ISubject {
	// <x, y, z>
	private String name;
	private Vector3f position;
	private Vector3f orientation;
	private Inventory inventory;
	private PhysicalHealth physicalHealth;
	
	public AbsSubject(String name, Vector3f position, Vector3f orientation, 
			Inventory inventory, PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.physicalHealth = physHealth;
		this.inventory = inventory;
	}
	
	public AbsSubject(String name, Vector3f position, Vector3f orientation,
			int slots, PhysicalHealth physHealth) {
		this.name = name;
		this.position = position;
		this.orientation = orientation;
		this.physicalHealth = physHealth;
		this.inventory = new Inventory(slots);
	}
	
	public ISubject translate(Vector3f delta) {
		position = position.add(delta);
		return this;
		
	}
	
	public ISubject rotate(Quaternionf quat) {
		orientation = orientation.rotate(quat);
		return this;
	}
	
	@Override
	public Camera getViewCamera() {
		// TODO: implement
		return null;
	}
	
	@Override
	public Camera get3rdCamera() {
		// TODO: implement
		return null;
	}
	
	// Getters and Setters
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Vector3f getPosition() {
		return position;
	}
	
	@Override
	public Vector3f getOrientation() {
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
