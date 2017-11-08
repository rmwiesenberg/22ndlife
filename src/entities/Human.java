package entities;

public class Human extends absSubject {

	private MentalHealth mentHealth;

	public Human(String name, float[] position, Inventory inventory, 
			MentalHealth mentHealth) {
		super(name, position, inventory, new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}

	public Human(String name, float[] position, int slots, 
			MentalHealth mentHealth) {
		super(name, position, new Inventory(slots), 
				new PhysicalHealth(20));
		this.mentHealth = mentHealth;
	}
	
	public MentalHealth getMentalHealth() {
		return mentHealth;
	}
}
