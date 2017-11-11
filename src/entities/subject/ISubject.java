package entities.subject;

import entities.item.Inventory;
import math.Pose;
import math.vector.Orientation;
import math.vector.Position;

import org.ejml.simple.*;

public interface ISubject {
	String getName();
	Pose getPose();
	Position getPosition();
	Orientation getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
