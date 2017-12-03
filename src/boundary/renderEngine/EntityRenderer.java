package boundary.renderEngine;

import java.util.ArrayList;

import org.lwjgl.opengl.*;

public class EntityRenderer {
	
	public static void render(Frame frame) {
		ArrayList<Integer> vaoIDs = frame.getVAOs();
		for(int vao: vaoIDs) {
			// Get used VAO
			GL30.glBindVertexArray(frame.getVertexArray(vao));
			
			// Enable first value of array to be read "vertex data"
			GL20.glEnableVertexAttribArray(0);
			
			// Enable first value of array to be read "texture data"
			GL20.glEnableVertexAttribArray(1);
			
			// Enable texture bank
			GL13.glActiveTexture(GL13.GL_TEXTURE0);                          
			
			// 2D texture ------
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, frame.getTexture(vao));
			
			// Draw Triangles based on model data vertex count
			GL11.glDrawElements(GL11.GL_TRIANGLES, frame.getVertexCount(vao), GL11.GL_UNSIGNED_INT, 0);
			
			// Unbind VBO from render data "vertex data"
			GL20.glDisableVertexAttribArray(0);
			
			// Unbind VBO from render data "texture data"
			GL20.glDisableVertexAttribArray(1);
			
			// Unbind VAO from render data
			GL30.glBindVertexArray(0);
			frame.cleanUp();
		}		
	}
}
