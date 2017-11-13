package entities.subject;

import org.joml.Vector3f;

import entities.item.Inventory;
import entities.subject.health.PhysicalHealth;
import math.Pose;

public interface ISubject {
	String getName();
	Pose getPose();
	Vector3f getPosition();
	Vector3f getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
