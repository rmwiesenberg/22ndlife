package entities.subject;

import entities.item.Inventory;
import math.Vec3f;

public class Human extends AbsSubject {

	private MentalHealth mentHealth;

	public Human(String name, Vec3f position, Vec3f orientation, 
			Inventory inventory, MentalHealth mentHealth) {
		super(name, position, orientation, inventory, new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}

	public Human(String name, Vec3f position, Vec3f orientation, 
			int slots,	MentalHealth mentHealth) {
		super(name, position, orientation, new Inventory(slots), 
				new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}
	
	public MentalHealth getMentalHealth() {
		return mentHealth;
	}
}
