package entities.subject;

import entities.item.Inventory;

public interface ISubject {
	String getName();
	float[] getPosition();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
