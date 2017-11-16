package boundary.renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.models.RawModel;
import controllers.handlers.ImageHandler;
import controllers.handlers.VoxelHandler;
import controllers.handlers.excpetions.InvalidImageSizeException;
import entities.Voxel;

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
	
}
