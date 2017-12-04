package boundary.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.models.RawModel;

public class EntityRenderer {
	
	public static void render(RawModel model) {
		// Get used VAO
		GL30.glBindVertexArray(model.getVaoID()); 
		// Enable first value of array to be read "vertex data"
		GL20.glEnableVertexAttribArray(0);						
		// Enable second value of array to be read "texture data"
		GL20.glEnableVertexAttribArray(1);
		// Enable texture bank
		GL13.glActiveTexture(GL13.GL_TEXTURE0);                          			
		// 2D texture ------
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getVaoID());
		// Draw Triangles based on model data vertex count
		GL11.nglDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		// Unbind VBO from render data
		GL20.glDisableVertexAttribArray(0);
		// Unbind VBO from render data
		GL20.glDisableVertexAttribArray(1);
		// Unbind VAO from render data
		GL30.glBindVertexArray(0);
	}
}
