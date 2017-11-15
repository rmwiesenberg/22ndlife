package boundary.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.models.RawModel;
import boundary.models.TexturedModel;

public class EntityRenderer {
	
	public static void render(TexturedModel model) {
		GL30.glBindVertexArray(model.getModel().getVaoID());													// Get used VAO
		GL20.glEnableVertexAttribArray(0);																		// Enable first value of array to be read "vertex data"
		GL20.glEnableVertexAttribArray(1);																		// Enable first value of array to be read "texture data"
		GL13.glActiveTexture(GL13.GL_TEXTURE0);																	// Enable texture bank
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());								// 2D texture ------		
		GL11.nglDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);	// Draw Triangles based on model data vertex count
		GL20.glDisableVertexAttribArray(0);																		// Unbind VBO from render data "vertex data"
		GL20.glDisableVertexAttribArray(1);																		// Unbind VBO from render data "texture data"
		GL30.glBindVertexArray(0);																				// Unbind VAO from render data	
	}
}
