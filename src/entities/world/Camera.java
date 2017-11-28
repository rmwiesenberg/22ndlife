package entities.world;

import org.joml.Vector3f;

public class Camera {
	private Vector3f pos;
	private Vector3f rot;
	
	public Camera(Vector3f pos, Vector3f rot) {
		this.pos = pos;
		this.rot = rot;
	}

	// Getters and Setters
	public Vector3f getPos() {
		return pos;
	}

	public Vector3f getRot() {
		return rot;
	}
}
