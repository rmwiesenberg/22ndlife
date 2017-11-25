package entities;

public class Voxel {
	private int id;
	private int[][] texture;
	
	// uv[][side]
	private int[][] uv;
	
	public Voxel(int id, int[][] texture, int[][] uv) {
		this.id = id;
		this.texture = texture;
		this.uv = uv;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public int[][] getTexture(){
		return texture;
	}
	
	public int[][] getUV() {
		return uv;
	}
	
	public int[] getSideUV(int side){
		return uv[side];
	}
}
