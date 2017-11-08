package entities;

public class PhysicalHealth extends AbsHealth {
	public PhysicalHealth(float maxHealth, float currentHealth) {
		super (maxHealth, currentHealth);
	}
	
	public PhysicalHealth(float maxHealth) {
		super (maxHealth);
	}
}
