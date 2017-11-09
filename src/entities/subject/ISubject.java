package entities.subject;

import entities.item.Inventory;
import math.Vec3f;

public interface ISubject {
	String getName();
	Vec3f getPosition();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
