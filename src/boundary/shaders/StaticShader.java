package boundary.shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram {
	
	private static final String vertexFile = "/boundary/shaders/vertexShader.txt";
	private static final String fragmentFile = "/boundary/shaders/fragmentShader.txt";

	int location_transformationMatrix;
	
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
		
	}
}
