package math;

import org.ejml.simple.SimpleMatrix;

public class Projection {
	private double fov;
	private double aspectRatio;
	private double zNear;
	private double zFar;
	
	public Projection(double fov, double aspectRatio, double zNear, double zFar) {
		this.fov = fov;
		this.aspectRatio = aspectRatio;
		this.zNear =zNear;
		this.zFar = zFar;
	}
	
	public SimpleMatrix getMatrix() {
		SimpleMatrix projection = new SimpleMatrix(4, 4);
		
		double zp = zFar + zNear;
		double zm = zFar - zNear;
		
		projection.set(0, 0, (1/Math.tan(fov/2))/aspectRatio);
		projection.set(1, 1, 1/Math.tan(fov)/2);
		projection.set(2, 2, -zp/zm);
		projection.set(3, 2, -1);
		projection.set(3, 2, -(2*zFar*zNear)/zm);
		
		return projection;
	}
	
	// Getters and Setters
	public double getFOV() {
		return fov;
	}
	
	public double getAspectRatio() {
		return aspectRatio;
	}
	
	public double getZFar() {
		return zFar;
	}
	
	public double getZNear() {
		return zNear;
	}
}
