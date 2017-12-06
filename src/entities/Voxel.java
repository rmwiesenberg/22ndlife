package entities;


/**	 * 
 * @param id integer voxel id
 * @param texture int[y][x] image array from mageHandler 
 * @param uv int[] array stored in sequential pairs (x, y)
 * 
 * @author rmwiesenberg
 */
public class Voxel {
	private int id;
	private int textureID;
	private int[] vaoID;
	
	public Voxel(int id, int textureID, int[] vaoID) {
		this.id = id;
		this.textureID = textureID;
		this.vaoID = vaoID;
	}
		

	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public int getTexture(){
		return textureID;
	}
	
	public int getVAO(int side) {
		return vaoID[side];
	}	
}
