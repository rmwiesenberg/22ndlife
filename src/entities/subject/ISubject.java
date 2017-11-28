package entities.subject;

import org.joml.Vector3f;

import entities.item.Inventory;
import entities.subject.health.PhysicalHealth;
import entities.world.Camera;

public interface ISubject {
	Camera getViewCamera();
	Camera get3rdCamera();
	
	// Getters and Setters
	String getName();
	Vector3f getPosition();
	Vector3f getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
