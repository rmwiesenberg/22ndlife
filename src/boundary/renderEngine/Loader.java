package boundary.renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class Loader {
	/**
	 * Converts float array data to a FloatBuffer
	 * 
	 * @param data
	 * @return
	 */
	public static FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		return buffer;
	}
	
	/**
	 * Converts int array into IntBuffer object
	 * 
	 * @param data
	 * @return
	 */
	public static IntBuffer storeDataInIntBuffer(int[] data) {
		
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
		return buffer;
	}
	
	/**
	 * Create vbo and give it an id to be used by other functions
	 * 
	 * @param data
	 * @param attributeNumber
	 * @param dimensions
	 * @return vboID
	 */
	public static int createAttributesVBO(float[] data, int attributeNumber, int dimensions) {
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
	
	public static int createIndicesVBO(int[] indices) {
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		Loader.storeDataInIntBuffer(indices);		
		
		return vboID;
	}
	
}
