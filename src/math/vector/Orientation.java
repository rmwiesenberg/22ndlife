package math.vector;

import org.ejml.simple.SimpleMatrix;

public class Orientation {
	private SimpleMatrix orientation;
	
	public Orientation(SimpleMatrix orientation) {
		this.orientation = orientation;
	}
	
	public Orientation(double roll, double pitch, double yaw) {
		SimpleMatrix orientation = new SimpleMatrix(3, 1);
		orientation.setColumn(0, 0, roll, pitch, yaw);
		this.orientation = orientation;
	}
	
	public Orientation move(Orientation delta) {
		orientation = orientation.plus(delta.get());
		return this;
	}
	
	public Orientation rotate(Quaterneon quat) {
		orientation = quat.getRotation().mult(orientation);
		return this;
	}
	
	// Getters and Setters
	public SimpleMatrix get() {
		return orientation;
	}
	
	public double roll() {
		return orientation.get(0);
	}
	
	public double pitch() {
		return orientation.get(1);
	}
	
	public double yaw() {
		return orientation.get(2);
	}
}
