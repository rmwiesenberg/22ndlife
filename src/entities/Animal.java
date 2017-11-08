package entities;

public class Animal extends AbsSubject {
	private int id;

	public Animal(String name, int id, float[] position, Inventory inventory, 
			PhysicalHealth physHealth) {
		super(name, position, inventory, physHealth);
		this.id = id;
	}

	public Animal(String name, int id, float[] position, int slots, 
			PhysicalHealth physHealth) {
		super(name, position, slots, physHealth);
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
