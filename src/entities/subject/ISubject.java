package entities.subject;

import entities.item.Inventory;
import math.Pose;
import math.vectors.Orientation;
import math.vectors.Position;

import org.ejml.simple.*;

public interface ISubject {
	String getName();
	Pose getPose();
	Position getPosition();
	Orientation getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
