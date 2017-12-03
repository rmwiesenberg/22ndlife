package entities.subject;

import org.joml.Vector3f;

import entities.item.Inventory;
import entities.subject.health.PhysicalHealth;

public interface ISubject {
	String getName();
	Vector3f getPosition();
	Vector3f getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
