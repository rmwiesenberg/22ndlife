package math;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Pose {
	private Vector3f position;
	private Vector3f orientation;
	
	public Pose(Vector3f position, Vector3f orientation) {
		this.position = position;
		this.orientation = orientation;
	}
	
	public Pose(float x, float y, float z, 
				float roll, float pitch, float yaw) {
		position = new Vector3f(x, y, z);
		orientation = new Vector3f(roll, pitch, yaw);
	}
	
	public Pose translate(Vector3f delta) {
		position = position.add(delta);
		return this;
	}
	
	public Pose rotate(Quaternionf quat) {
		orientation = quat.transform(orientation);
		return this;
	}
	
	public Pose relativePose(Pose rel) {
		return rel;
		// TODO
	}
	
	// Getters and Setters
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getOrientation() {
		return orientation;
	}
}
