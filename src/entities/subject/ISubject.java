package entities.subject;

import entities.item.Inventory;
import entities.subject.health.PhysicalHealth;
import math.Pose;
import math.vector.Orientation;
import math.vector.Position;

public interface ISubject {
	String getName();
	Pose getPose();
	Position getPosition();
	Orientation getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
