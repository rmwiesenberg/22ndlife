package entities;

public class Human extends absSubject {

	private MentalHealth mentHealth;

	public Human(float[] position, Inventory inventory, 
			MentalHealth mentHealth) {
		super(position, inventory, new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}

	public Human(float[] position, int slots, 
			MentalHealth mentHealth) {
		super(position, new Inventory(slots), 
				new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}
}
