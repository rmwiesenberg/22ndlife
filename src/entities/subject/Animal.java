package entities.subject;

import entities.item.Inventory;
import math.Vec3f;

public class Animal extends AbsSubject {
	private int id;

	public Animal(String name, int id, Vec3f position, Vec3f orientation, 
			Inventory inventory, PhysicalHealth physHealth) {
		super(name, position, orientation, inventory, physHealth);
		this.id = id;
	}

	public Animal(String name, int id, Vec3f position, Vec3f orientation, 
			int slots,	PhysicalHealth physHealth) {
		super(name, position, orientation, new Inventory(slots), 
				physHealth);
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
