package boundary.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.models.RawModel;

public class EntityRenderer {
	
	public static void render(RawModel model) {
		GL30.glBindVertexArray(model.getVaoID());							// Get used VAO
		GL20.glEnableVertexAttribArray(0);									// Enable first value of array to be read "vertex data"
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());	// Draw Triangles based on model data vertex count
		GL20.glDisableVertexAttribArray(0);									// Unbind VBO from render data
		GL30.glBindVertexArray(0);											// Unbind VAO from render data	
	}
}
