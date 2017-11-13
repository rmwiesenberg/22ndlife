package entities.subject.beasts;

import entities.item.Inventory;
import entities.subject.AbsSubject;
import entities.subject.health.MentalHealth;
import entities.subject.health.PhysicalHealth;
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
