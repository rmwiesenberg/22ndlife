package boundary.engine.graph;

import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.*;

import math.Pose;
import math.vector.Orientation;
import math.vector.Position;
import math.vector.Quaterneon;

public class Camera {

    private Pose pose;
    
    public Camera() {
        pose = new Pose(0, 0, 0, 0, 0, 0);
    }
    
    public Camera(Position position, Orientation orientation) {
        pose = new Pose(position, orientation);
    }
    
    public Camera translate(Position delta) {
        pose = pose.translate(delta);
        return this;
    }

    public Camera rotate(Quaterneon quat) {
        pose = pose.rotate(quat);
        return this;
    }
    
    public Camera movePosition(double deltaX, double deltaY, double deltaZ) {
    	double dcamX = 0;
    	double dcamY = 0;
    	double dcamZ = 0;
    	double yaw = pose.getOrientation().yaw();
    	if ( deltaY != 0 ) {
            dcamX += (float)Math.sin(Math.toRadians(yaw)) * -1.0f * deltaY;
            dcamX += (float)Math.cos(Math.toRadians(yaw)) * deltaY;
        }
        if ( deltaX != 0) {
            dcamX += (float)Math.sin(Math.toRadians(yaw - 90)) * -1.0f * deltaX;
            dcamY += (float)Math.cos(Math.toRadians(yaw - 90)) * deltaX;
        }
        dcamZ += deltaZ;
        
        pose = pose.translate(new Position(dcamX, dcamY, dcamZ));
        
        return this;
    }
    
    public Camera moveRotation(double deltaRoll, double deltaPitch, double deltaYaw) {
    	
    }

    // Getters and Setters
    public Position getPosition() {
        return pose.getPosition();
    }
    
    public Orientation getOrientation() {
    	return pose.getOrientation();
    }

    public Camera setPosition(double x, double y, double z) {
        pose = new Pose(new Position(x, y, z), getOrientation());
        return this;
    }
    
    public Camera setOrientation(double roll, double pitch, double yaw) {
    	pose = new Pose(getPosition(), new Orientation(roll, pitch, yaw));
    	return this;
    }
    
    public SimpleMatrix getViewMatrix() {
    	SimpleMatrix viewMatrix = SimpleMatrix.identity(4);
    	
    	
    	
        return viewMatrix;
    }
    
    
}