package boundary.renderEngine;

import java.io.IOException;
import java.nio.ByteBuffer;
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
	
	static List<Integer> vaos = new ArrayList<Integer>();
	static List<Integer> vbos = new ArrayList<Integer>();
	static List<Integer> textures = new ArrayList<Integer>();
	
	public int loadToVAO(float[] vertices, int[] indices, float[] uv) {
		
		int vaoID = createVAO();
		storeDataInAttributeList(vertices, 0, 3);
		storeDataInAttributeList(uv, 1, 2);
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);
		
		return vaoID;	
	}
	
	private int createVAO() {

		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		
		return vaoID;
		
	}
	
	public int loadTexture(int[] colors, int width, int height) {
		int textureID = GL11.glGenTextures(); 
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); 
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 
                		  0, 
                		  GL30.GL_RGBA32I,
                		  width, 
                		  height, 
                		  0, 
                		  GL30.GL_RGBA_INTEGER, 
                		  GL11.GL_INT, 
                		  storeDataInIntBuffer(colors));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0); 
		return textureID;
		
	}
	
	private void storeDataInAttributeList(float[] data, int attributeNumber, int dimentions) {
		
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, dimentions, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	
	private void bindIndicesBuffer(int[] indices) {
		
		int vboID =GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		
	}
	
	IntBuffer storeDataInIntBuffer(int[] data) {
		
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
		
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
		
	}
	
	private ByteBuffer storeDataInByteBuffer(byte[] data) {		
		ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;		
	}
	
	public void cleanUp() {
		
		for (int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		
		for (int texture : textures) {
			GL11.glDeleteTextures(texture);
		}
		
	}

}
