package math;

import math.vectors.*;

public class Pose {
	private Position position;
	private Orientation orientation;
	
	public Pose(Position position, Orientation orientation) {
		this.position = position;
		this.orientation = orientation;
	}
	
	public Pose translate(Position delta) {
		position = position.translate(delta);
		return this;
	}
	
	public Pose rotate(Quaterneon quat) {
		orientation = orientation.rotate(quat);
		return this;
	}
	
	// Getters and Setters
	public Position getPosition() {
		return position;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
}
