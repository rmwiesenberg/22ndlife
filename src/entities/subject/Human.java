package entities.subject;

import entities.item.Inventory;
import math.Pose;

public class Human extends AbsSubject {

	private MentalHealth mentHealth;

	public Human(String name, Pose pose, Inventory inventory, 
			MentalHealth mentHealth) {
		super(name, pose, inventory, new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}

	public Human(String name, Pose pose, int slots,	
			MentalHealth mentHealth) {
		super(name, pose, new Inventory(slots), new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}
	
	public MentalHealth getMentalHealth() {
		return mentHealth;
	}
}
