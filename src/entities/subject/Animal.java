package entities.subject;

import org.joml.Vector3f;

import entities.item.Inventory;
import entities.subject.health.PhysicalHealth;

public class Animal extends AbsSubject {
	private int id;

	public Animal(String name, int id, Vector3f position, Vector3f orientation, 
			Inventory inventory, PhysicalHealth physHealth) {
		super(name, position, orientation, inventory, physHealth);
		this.id = id;
	}

	public Animal(String name, int id, Vector3f position, Vector3f orientation, 
			int slots, PhysicalHealth physHealth) {
		super(name, position, orientation, new Inventory(slots), physHealth);
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
