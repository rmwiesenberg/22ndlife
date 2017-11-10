package entities.subject;

import entities.item.Inventory;

import org.ejml.simple.*;

public interface ISubject {
	String getName();
	SimpleMatrix getPosition();
	SimpleMatrix getOrientation();
	Inventory getInventory();
	PhysicalHealth getPhysicalHealth();
}
