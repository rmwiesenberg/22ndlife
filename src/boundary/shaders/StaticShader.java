package boundary.shaders;

import entities.world.Camera;
import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {
	
	private static final String vertexFile = "src/boundary/shaders/vertexShader.txt";
	private static final String fragmentFile = "src/boundary/shaders/fragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
		
	}

	@Override
	protected void getAllUniformLocations() {
		
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		
	}

    public void loadTransformationMatrix(Matrix4f transformationMatrix) {
	    super.loadMatrixToUniform(location_transformationMatrix, transformationMatrix);
    }

    public void loadProjectionMatrix(Matrix4f projectionMatrix) {
        super.loadMatrixToUniform(location_projectionMatrix, projectionMatrix);
    }

    public void loadViewMatrix(Matrix4f viewMatrix) {
		super.loadMatrixToUniform(location_viewMatrix, viewMatrix);
	}

	public void loadViewMatrix(Camera camera) {
	    loadViewMatrix(new Matrix4f().translate(camera.getPos()).rotateXYZ(camera.getRot()));
    }
}
