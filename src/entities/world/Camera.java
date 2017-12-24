package entities.world;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
	private Vector3f pos;
	private Vector3f rot;
	private Vector3f posSpeed;
	private Vector3f rotSpeed;
	
	public Camera(Vector3f pos, Vector3f rot) {
		this.pos = pos;
		this.rot = new Vector3f((float) Math.toRadians(90),
                                (float) Math.toRadians(0),
                                (float) Math.toRadians(90));
		this.rot.add(rot);
		this.posSpeed = new Vector3f(.1f, .1f, .1f);
		this.rotSpeed = new Vector3f(1f, 1f, 1f);
	}

	public void posSpeed(Vector3f vec) {
	    vec.normalize();
	    Vector3f delta = new Vector3f(vec.x*posSpeed.x,
                                      vec.y*posSpeed.y,
                                      vec.z*posSpeed.z);
		move(delta);
	}

	public void rotSpeed(Vector3f vec) {
        Vector3f delta = new Vector3f(vec.x*rotSpeed.x,
                                      vec.y*rotSpeed.y,
                                      vec.z*rotSpeed.z);
        rotate(delta);
    }

	public void translate(Vector3f translation) {
		pos = pos.add(translation);
	}

	public void move(Vector3f vector) {
        pos.add(new Matrix4f().translate(vector).rotateXYZ(rot).getTranslation(vector));
    }

	public void rotate(Vector3f rotation) {
        rot = rot.add(rotation);
    }

	// Getters and Setters
	public Vector3f getPos() {
		return pos;
	}

	public Vector3f getRot() {
		return rot;
	}
}
