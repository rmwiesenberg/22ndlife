package entities;

public class Human extends absSubject {

	public Human(float[] position, Inventory inventory) {
		super(position, inventory, 
				new PhysicalHealth(20), new MentalHealth(10));
	}

	public Human(float[] position, int slots) {
		super(position, new Inventory(slots), 
				new PhysicalHealth(20), new MentalHealth(10));
	}
}
