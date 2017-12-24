package entities.world;

import org.joml.Vector3f;

public class Camera {
	private Vector3f pos;
	private Vector3f rot;
	private float speed;
	
	public Camera(Vector3f pos, Vector3f rot) {
		this.pos = pos;
		this.rot = rot;
		this.speed = 1f;
	}

	public void moveSpeed(Vector3f vec) {
		translate(vec.normalize(speed));
	}

	public void translate(Vector3f trans) {
		pos = pos.add(trans);
	}

	// Getters and Setters
	public Vector3f getPos() {
		return pos;
	}

	public Vector3f getRot() {
		return rot;
	}
}
