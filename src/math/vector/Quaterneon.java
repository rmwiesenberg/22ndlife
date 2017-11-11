package math.vector;

import org.ejml.simple.SimpleMatrix;
import java.lang.Math;

public class Quaterneon {
	private SimpleMatrix quat;
	
	public Quaterneon(SimpleMatrix quat) {
		this.quat = quat;
	}
	
	public Quaterneon(double w, double x, double y, double z) {
		SimpleMatrix quat = new SimpleMatrix(4, 1);
		quat.setColumn(0, 0, w, x, y, z);
		this.quat = quat;
	}
	
	public Orientation getEuler() {
		// roll
		double sinr = 2*(w()*x()+y()*z());
		double cosr = 1-(2*(x()*x()+y()*y()));
		double roll = Math.atan2(sinr, cosr);
		
		// pitch
		double sinp = 2*(w()*y()-z()*x());
		double pitch;
		if(Math.abs(sinp) >= 1){
			pitch = Math.signum(sinp)*Math.PI/2;
		} else {
			pitch = Math.asin(sinp);
		}
		
		// yaw
		double siny = 2*(w()*z()+x()*y());
		double cosy = 1-2*(y()*y()+z()*z());
		double yaw = Math.atan2(siny, cosy);
		
		// make euler		
		return new Orientation(roll, pitch, yaw);
	}
	
	public SimpleMatrix getRotation() {
		SimpleMatrix rotation = new SimpleMatrix(3,3);
		
		rotation.set(0, 0, w()*w()+x()*x()-y()*y()-z()*z());
		rotation.set(1, 0, 2*(x()*y()+w()*z()));
		rotation.set(2, 0, 2*(x()*z()+w()*y()));
		
		rotation.set(0, 1, 2*(x()*y()-w()*z()));
		rotation.set(1, 1, w()*w()-x()*x()+y()*y()-z()*z());
		rotation.set(2, 1, 2*(w()*x()+y()*z()));
		
		rotation.set(0, 2, 2*(w()*y()+x()*z()));
		rotation.set(1, 2, 2*(y()*z()-w()*x()));
		rotation.set(2, 2, w()*w()-x()*x()-y()*y()+z()*z());
		
		return rotation;
		
	}
	
	// Getters in Setters
	public SimpleMatrix get() {
		return quat;
	}
	
	public double w() {
		return quat.get(0);
	}
	
	public double x() {
		return quat.get(1);
	}
	
	public double y() {
		return quat.get(2);
	}
	
	public double z() {
		return quat.get(3);
	}
}
