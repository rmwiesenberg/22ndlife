package boundary.renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.models.RawModel;

public class Loader {
	
	static List<Integer> vaos = new ArrayList<Integer>();			// Array List of vaos to be used by program
	static List<Integer> vbos = new ArrayList<Integer>();			// Array List of vbos to be used by program

	public RawModel loadToVAO(float[] vertices, int[] indices) {	// Loads an array of verticies into a vao created at call
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, 3);					// Depth of 3 3D Matrix
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);									// Unbind vertex array
		
		return new RawModel(vaoID, indices.length);
	}
	
	private int createVAO() {									// Create vao and give it an id to be used by other functions
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		
		return vaoID;
	}
	
	private void storeDataInAttributeList(float[] data, int attributeNumber, int dimensions) {		// Adds used vbo to vbo list and stores float array into it
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	IntBuffer storeDataInIntBuffer(int[] data) {							// Converts int array into IntBuffer object
		
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	private void bindIndicesBuffer(int[] indices) {							// Binds integer array of indicies to the selected vbo
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);		
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {				// Converts float array data to a FloatBuffer
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	public void cleanUp() {						// Removes all vaos and vbos from memory cache
		
		for (int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
	}
	
}
