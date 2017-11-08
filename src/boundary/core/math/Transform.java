package boundary.core.math;

import boundary.core.kernel.Camera;

public class Transform {

	private Vec3f translation;
	private Vec3f rotation;
	private Vec3f scaling;
	
	public Transform() {
		setTranslation(new Vec3f(0, 0, 0));
		setRotation(new Vec3f(0, 0, 0));
		setScaling(new Vec3f(0, 0, 0));
	}
	
	public Matrix4f getWorldMatrix() {
		
		Matrix4f translationMatrix = new Matrix4f().Translation(translation);
		Matrix4f rotationMatrix = new Matrix4f().Rotation(rotation);
		Matrix4f scalingMatrix = new Matrix4f().Scaling(scaling);
		
		return translationMatrix.mul(scalingMatrix.mul(rotationMatrix));
	}
	
	public Matrix4f getModelMatrix() {
		
		Matrix4f rotationMatrix = new Matrix4f().Rotation(rotation);
		return rotationMatrix;
	}
	
	public Matrix4f getMVP() {
		
		return Camera.getInstance().getViewProjectionMatrix().mul(getWorldMatrix());
	}
	
	public Vec3f getTranslation() {
		return translation;
	}
	public void setTranslation(Vec3f translation) {
		this.translation = translation;
	}
	public Vec3f getRotation() {
		return rotation;
	}
	public void setRotation(Vec3f rotation) {
		this.rotation = rotation;
	}
	public Vec3f getScaling() {
		return scaling;
	}
	public void setScaling(Vec3f scaling) {
		this.scaling = scaling;
	}
	
	
}
