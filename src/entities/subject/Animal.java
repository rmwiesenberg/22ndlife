package entities.subject;

import entities.item.Inventory;
import math.Vec3f;

public class Animal extends AbsSubject {
	private int id;

	public Animal(String name, int id, Vec3f position, Inventory inventory, 
			PhysicalHealth physHealth) {
		super(name, position, inventory, physHealth);
		this.id = id;
	}

	public Animal(String name, int id, Vec3f position, int slots, 
			PhysicalHealth physHealth) {
		super(name, position, slots, physHealth);
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
