package math.vectors;

import org.ejml.simple.SimpleMatrix;

import math.Projection;

public class Position {
	private SimpleMatrix position;
	
	public Position(SimpleMatrix position) {
		this.position = position;
	}
	
	public Position(double x, double y, double z) {
		SimpleMatrix position = new SimpleMatrix(3, 1);
		position.setColumn(0, 0, x, y, z);
		this.position = position;
	}
	
	public Position translate(Position delta) {
		position = position.plus(delta.get());
		return this;
	}
	
	public SimpleMatrix getProjected(Projection projection) {
		return projection.getMatrix().mult(getVec4());
	}
	
	// Getters and Setters
	public SimpleMatrix get() {
		return position;
	}
	
	public SimpleMatrix getVec4() {
		SimpleMatrix vec4 = new SimpleMatrix(4, 1);
		vec4.insertIntoThis(0, 0, position);
		vec4.set(1);
		return vec4;
	}
	
	public double x() {
		return position.get(0);
	}
	
	public double y() {
		return position.get(1);
	}
	
	public double z() {
		return position.get(2);
	}
}
