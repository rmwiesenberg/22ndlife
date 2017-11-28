package entities.subject;

import org.joml.Vector3f;

import entities.item.Inventory;
import entities.subject.health.MentalHealth;
import entities.subject.health.PhysicalHealth;

public class Human extends AbsSubject {

	private MentalHealth mentHealth;

	public Human(String name, Vector3f position, Vector3f orientation, Inventory inventory, 
			MentalHealth mentHealth) {
		super(name, position, orientation, inventory, new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}

	public Human(String name, Vector3f position, Vector3f orientation, int slots,	
			MentalHealth mentHealth) {
		super(name, position, orientation, new Inventory(slots), 
				new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}
	
	public MentalHealth getMentalHealth() {
		return mentHealth;
	}
}
