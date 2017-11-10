package entities.subject;

import entities.item.Inventory;
import math.Pose;

public class Animal extends AbsSubject {
	private int id;

	public Animal(String name, int id, Pose pose, Inventory inventory, PhysicalHealth physHealth) {
		super(name, pose, inventory, physHealth);
		this.id = id;
	}

	public Animal(String name, int id, Pose pose, int slots, PhysicalHealth physHealth) {
		super(name, pose, new Inventory(slots), physHealth);
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
