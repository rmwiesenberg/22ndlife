package boundary.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram {
	
	int programID;
	int vertexShaderID;
	int fragmentShaderID;
	
	public ShaderProgram(String vertexFile, String fragmentFile) {					// Constructor takes the file names for both shaders and loads them into memory
																					// --- Vertex shader does points in 3d space --- fragment shader does colour ---
		programID = GL20.glCreateProgram();
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		
		GL20.glAttachShader(programID, vertexShaderID);								// Bind shader to programID
		GL20.glAttachShader(programID, fragmentShaderID);
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
	}
	
	
	
	private int loadShader(String file, int type) {									// Read shader file input by filename and shader type
																					// 		and store it in a string buffer
		StringBuilder shaderSource = new StringBuilder();
		
		InputStream in = Class.class.getResourceAsStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String line;
		try {
			while((line = reader.readLine()) != null) {
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not Load Shader File");
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);							// Creates shader by file and loads it into a new id
		GL20.glCompileShader(shaderID);
		
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {				// Get gl compile status of shader
			
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));				// In case of error, force close and print what went wrong
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		
		return shaderID;
		
	}
	
}
