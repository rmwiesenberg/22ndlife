package boundary.renderEngine;

import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.opengl.GL11;

import boundary.models.RawModel;

public class MasterRenderer {

	public void prepare() {
		// Set the clear "background" color
		glClearColor(0.529f, 0.807f, 0.980f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
	}
	
	public void render(RawModel model) {
		
		EntityRenderer.render(model);
	}
}
