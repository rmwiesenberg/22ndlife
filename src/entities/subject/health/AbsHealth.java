package entities.subject.health;

import entities.exceptions.NotEnoughHealthException;

public abstract class AbsHealth implements IHealth {
	protected float maxHealth;
	protected float currentHealth;
	
	public AbsHealth(float maxHealth, float currentHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
	}
	
	public AbsHealth(float maxHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}

	@Override
	public float getCurrentHealth() {
		return currentHealth;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public void changeCurrentHealth(float healthToChange) throws NotEnoughHealthException {
		
		float val = (currentHealth + healthToChange);

		if (val < 0) {
			throw new NotEnoughHealthException();
		}else if (val >= maxHealth) {
			currentHealth = maxHealth;
		}else {
			currentHealth = val;
		}
	}

}
