package entities;

import entities.exceptions.InvalidSidesException;

public class Voxel {
	private int id;
	private int[][][] sides;
	
	public Voxel(int id, int[][] side) {
		this.id = id;
		for(int i = 0; i < 6; i++) {
			sides[i] = side;
		}		
	}
	
	public Voxel(int id, int[][] top, int[][] side) {
		this.id = id;
		sides[0] = top;
		for(int i = 1; i < 6; i++) {
			sides[i] = side;
		}		
	}
	
	public Voxel(int id, int[][] top, int[][] bottom, int[][] side) {
		this.id = id;
		sides[0] = top;
		sides[1] = bottom;
		for(int i = 2; i < 6; i++) {
			sides[i] = side;
		}
	}
	
	public Voxel(int id, int[][] top, int[][] bottom, 
			int[][] side1, int[][] side2, int[][] side3, int[][] side4) {
		this.id = id;
		sides[0] = top;
		sides[1] = bottom;
		sides[2] = side1;
		sides[3] = side2;
		sides[4] = side3;
		sides[5] = side4;
	}
	
	public Voxel(int id, int[][][] sides) throws InvalidSidesException {
		this.id = id;
		if(sides.length != 6) {
			throw new InvalidSidesException();
		} else {
			this.sides = sides;
		}
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public int[][][] getSides(){
		return sides;
	}
	
	public int[][] getSide(int side){
		return sides[side];
	}
	
	public int[][] getTop(){
		return getSide(0);
	}
	
	public int[][] getBottom(){
		return getSide(1);
	}
	
	public int[][] getSide1(){
		return getSide(2);
	}
	public int[][] getSide2(){
		return getSide(3);
	}
	public int[][] getSide3(){
		return getSide(4);
	}
	public int[][] getSide4(){
		return getSide(5);
	}	
}
