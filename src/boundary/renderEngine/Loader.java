package boundary.renderEngine;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

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
	
	public int loadTexture(byte[] colors, int width, int height) {
		// enable redering to Texture_2d
		glEnable(GL_TEXTURE_2D);
		
		// get dat texID
		int textureID = GL11.glGenTextures(); 
		
		// bind
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		
		// this informs the gpu how to read bytes. Important if you enjoy more than one color
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		
		// this does blending. We are smart so we don't need it for voxels
		GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		
		// texture binderoni
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 
                		  0, 
                		  GL11.GL_RGBA,
                		  width, 
                		  height, 
                		  0, 
                		  GL11.GL_RGBA, 
                		  GL11.GL_UNSIGNED_BYTE, 
                		  storeDataInByteBuffer(colors));
		
		// mipmap
		GL30.glGenerateMipmap(GL_TEXTURE_2D);
		
		// unbind
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
