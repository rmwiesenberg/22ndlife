package boundary.renderEngine;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Frame {
	private ArrayList<Integer> vaoIDs = new ArrayList<Integer>();
	private HashMap<Integer, ArrayList<Integer>> vboIDs = new HashMap<Integer, ArrayList<Integer>>();
	private HashMap<Integer, Integer> textureIDs = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> vertexIDs = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> vertexNum = new HashMap<Integer, Integer>();
	
	
	/**
	 * Create vao and give it an id to be used by other functions
	 * 
	 * @param vertices
	 * @param indices
	 * @param uv
	 * @return vaoID
	 */
	public int createVAO(float[] vertices, int[] indices, float[] uv) {
		int vaoID = GL30.glGenVertexArrays();
		vaoIDs.add(vaoID);
		vboIDs.put(vaoID, new ArrayList<Integer>());
		
		int vboID = -1;

		vertexNum.put(vaoID, vertices.length);
		vboID = createAttributesVBO(vertices, 0, 3);
		vboIDs.get(vaoID).add(vboID);
		vertexIDs.put(vaoID, vboID);
		
		vboID = createAttributesVBO(uv, 1, 2);
		vboIDs.get(vaoID).add(vboID);		
		
		vboID = createIndicesVBO(indices);
		vboIDs.get(vaoID).add(vboID);
		
		GL30.glBindVertexArray(vaoID);
		
		return vaoID;
	}
	
	/**
	 * Create vbo and give it an id to be used by other functions
	 * 
	 * @param data
	 * @param attributeNumber
	 * @param dimensions
	 * @return vboID
	 */
	private int createAttributesVBO(float[] data, int attributeNumber, int dimensions) {
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		Loader.storeDataInFloatBuffer(data);
		GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vboID;
	}
	
	/**
	 * Binds integer array of indicies to the selected vbo
	 * 
	 * @param indices
	 * @return vboID
	 */
	
	private int createIndicesVBO(int[] indices) {
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		Loader.storeDataInIntBuffer(indices);		
		
		return vboID;
	}
	
	/**
	 * Removes all vaos and vbos from memory cache
	 */
	public void cleanUp() {
		
		for (int vao : vaoIDs) {
			for (int vbo : vboIDs.get(vao)) {
				
			}
			GL11.glDeleteTextures(getTexture(vao));
			GL30.glDeleteVertexArrays(vao);
		}		
	}
	
	

	// Getters and Setters
	public ArrayList<Integer> getVAOs() {
		return vaoIDs;
	}
	
	public int getTexture(int vao) {
		return textureIDs.get(vao);
	}
	
	public int getVertexArray(int vao) {
		return vertexIDs.get(vao);
	}
	
	public int getVertexCount(int vao) {
		return vertexNum.get(vao);
	}
	
}
