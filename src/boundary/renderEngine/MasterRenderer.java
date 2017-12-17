package boundary.renderEngine;

import boundary.shaders.StaticShader;
import org.lwjgl.opengl.GL11;

import entities.Voxel;
import entities.world.Camera;
import entities.world.World;

public class MasterRenderer {
	
	public void prepare() {
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
	}
	
//	public void renderVoxel(Voxel voxel, int side, StaticShader shader) {
//		EntityRenderer.renderVoxelSide(voxel, side, shader);
//	}
//	
	public void renderWorld(World world, Camera camera, StaticShader shader) {
		EntityRenderer.renderWorld(world, camera, shader);
	}
}
