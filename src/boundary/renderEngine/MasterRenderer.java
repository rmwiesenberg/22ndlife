package boundary.renderEngine;

import boundary.shaders.StaticShader;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import entities.world.Camera;
import entities.world.World;

public class MasterRenderer {

    private Matrix4f projectionMatrix;

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 10000;

    public MasterRenderer(int width, int height, StaticShader shader){
        createProjectionMatrix(width, height);
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
	
	public void prepare() {
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
	}

	public void renderWorld(World world, Camera camera, StaticShader shader) {
		EntityRenderer.renderWorld(world, camera, shader);
	}

	public void createProjectionMatrix(int width, int height) {

		projectionMatrix = new Matrix4f();

		float aspect = (float) width / (float) height;
		float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
		float xScale = yScale / aspect;
		float zp = FAR_PLANE + NEAR_PLANE;
		float zm = FAR_PLANE - NEAR_PLANE;

		// TODO: FIX MEEEEEE
//		projectionMatrix.m00(xScale);
//		projectionMatrix.m11(yScale);
//		projectionMatrix.m22(-zp/zm);
//		projectionMatrix.m23(-1f);
//		projectionMatrix.m32(-(2f * FAR_PLANE * NEAR_PLANE) / zm);
//		projectionMatrix.m33(0);
	}
}
