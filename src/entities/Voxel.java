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
	private int textureWidth;
	private int textureHeight;
	private boolean hasAlphhaChannel;
	private byte[] texture;
	
	// uv[][side]
	private int[][] uv;
	
	public Voxel(int id, int textureWidth, int textureHeight, boolean hasAlphaChannel, 
			byte[] texture, int[][] uv) {
		this.id = id;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.hasAlphhaChannel = hasAlphaChannel;
		this.texture = texture;
		this.uv = uv;
	}
		

	// Getters and Setters
	public int getId() {
		return id;
	}
	
	public byte[] getTexture(){
		return texture;
	}
	
	public int getTextureWidth() {
		return textureWidth;
	}

	public int getTextureHeight() {
		return textureHeight;
	}

	public boolean hasAlphhaChannel() {
		return hasAlphhaChannel;
	}
	
	public int[][] getUV() {
		return uv;
	}
	
	public int[] getSideUV(int side){
		return uv[side];
	}
}
