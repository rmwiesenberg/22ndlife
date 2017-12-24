package entities.world;

import org.joml.Vector3f;

public class Camera {
	private Vector3f pos;
	private Vector3f rot;
	private Vector3f posSpeed;
	private Vector3f rotSpeed;
	
	public Camera(Vector3f pos, Vector3f rot) {
		this.pos = pos;
		this.rot = rot;
		this.posSpeed = new Vector3f(.1f, .1f, .1f);
		this.rotSpeed = new Vector3f(1f, 1f, 1f);
	}

	public void moveFoward(float scale) {
        Vector3f vecX = new Vector3f(1, 0, 0);
        pos.add(vecX.mul(-scale * posSpeed.x));
    }

    public void moveRight(float scale) {
	    Vector3f vecY = new Vector3f(0, 1, 0);
	    pos.add(vecY.mul(-scale * posSpeed.y));
    }

    public void moveUp(float scale) {
        Vector3f vecZ = new Vector3f(0, 0, 1);
        pos.add(vecZ.mul(scale * posSpeed.z));
    }

	public void moveRot(Vector3f vec) {
        Vector3f delta = new Vector3f(vec.x*rotSpeed.x,
                                      vec.y*rotSpeed.y,
                                      vec.z*rotSpeed.z);
        rotate(delta);
    }

	public void translate(Vector3f translation) {
		pos = pos.add(translation);
	}


	public void rotate(Vector3f rotation) {
        rot.add(rotation);
    }

	// Getters and Setters
	public Vector3f getPos() {
		return pos;
	}

	public Vector3f getRot() {
	    return rot;
	}
}
