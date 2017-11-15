package boundary.shaders;

public class StaticShader extends ShaderProgram{
	
	private static final String vertexFile = "/boundary/shaders/vertexShader.txt";
	private static final String fragmentFile = "/boundary/shaders/fragmentShader.txt";

	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0);			// Position from slot 1 of vao
		super.bindAttribute("textureCoords", 1);	// Texture from slot 2 of vao
	}
	
}
