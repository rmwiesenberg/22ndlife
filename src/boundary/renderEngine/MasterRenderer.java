package boundary.renderEngine;

import boundary.DisplayManager;
import boundary.shaders.StaticShader;
import entities.world.Camera;
import entities.world.World;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class MasterRenderer {
	private DisplayManager displayManager;
	private StaticShader shader;

    private Matrix4f projectionMatrix;

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 10000;

    public MasterRenderer(DisplayManager displayManager, StaticShader shader){
    	this.displayManager = displayManager;
    	this.shader = shader;

        createProjectionMatrix(displayManager.getWidth(), displayManager.getHeight());
        startShader();
        shader.loadProjectionMatrix(projectionMatrix);
        stopShader();
    }
	
	public void prepare() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void renderWorld(World world, Camera camera, StaticShader shader) {
        shader.loadViewMatrix(camera);
		EntityRenderer.renderWorld(world, camera, shader);
	}

	private void createProjectionMatrix(int width, int height) {

		projectionMatrix = new Matrix4f();

		float aspect = (float) width / (float) height;
		float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
		float xScale = yScale / aspect;
		float zp = FAR_PLANE + NEAR_PLANE;
		float zm = FAR_PLANE - NEAR_PLANE;

		// TODO: FIX MEEEEEE
		projectionMatrix.m00(xScale);
		projectionMatrix.m11(yScale);
		projectionMatrix.m22(-zp/zm);
		projectionMatrix.m23(-1f);
		projectionMatrix.m32(-(2f * FAR_PLANE * NEAR_PLANE) / zm);
		projectionMatrix.m33(0);
	}

	public void cleanUp(){
        shader.cleanUp();
    }

    public void startShader(){
        shader.start();
    }

    public void stopShader(){
        shader.stop();
    }

	// Getters and Setters
    public DisplayManager getDisplayManager() {
        return displayManager;
    }

    public StaticShader getShader() {
        return shader;
    }
}
