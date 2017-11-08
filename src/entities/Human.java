package entities;

public class Human extends Subject {

	public Human(float[] position, Inventory inventory, float healthInput) {
		super(position, inventory, healthInput);
		healthInput = 20;
	}

	public Human(float[] position, int slots, float healthInput) {
		super(position, slots, healthInput);
		healthInput = 20;
	}

}
