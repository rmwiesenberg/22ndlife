package boundary.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.models.RawModel;
import boundary.models.TexturedModel;

public class EntityRenderer {
	
	public static void render(Frame frame) {
		int[] vaos = frame.getVAOs();
		for(int i = 0; i < vaos.length; i++) {
			int vaoID = vaos[i];
			// Get used VAO
			GL30.glBindVertexArray(frame.getVertexArray(vaoID));
			// Enable first value of array to be read "vertex data"
			GL20.glEnableVertexAttribArray(0);
			// Enable first value of array to be read "texture data"
			GL20.glEnableVertexAttribArray(1);
			// Enable texture bank
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			// 2D texture ------
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, frame.getTexture(vaoID));
			// Draw Triangles based on model data vertex count
			GL11.nglDrawElements(GL11.GL_TRIANGLES, frame.getVertexCount(vaoID), GL11.GL_UNSIGNED_INT, 0);
			// Unbind VBO from render data "vertex data"
			GL20.glDisableVertexAttribArray(0);
			// Unbind VBO from render data "texture data"
			GL20.glDisableVertexAttribArray(1);
			// Unbind VAO from render data
			GL30.glBindVertexArray(0);
		}		
	}
}
