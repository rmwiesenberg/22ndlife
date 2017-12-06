package boundary.renderEngine;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import boundary.Textures.ModelTexture;
import boundary.models.RawModel;
import boundary.models.TexturedModel;
import boundary.shaders.StaticShader;
import controllers.parsers.VoxelParser;
import controllers.parsers.exceptions.InvalidImageSizeException;
import entities.Voxel;

import java.nio.*;
import java.util.HashMap;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayManager {

	// The window handle
	private long window;
	private final int WIDTH = 1080;
	private final int HEIGHT = 720;
	// An fps of 1 is 60fps
	private final int FPS = 1;
	public static Loader loader1 = null;
	public static StaticShader shader1 = null;
	
	public void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(WIDTH, HEIGHT, "22nd Life", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(FPS);

		// Make the window visible
		glfwShowWindow(window);
	}

	public void loop() {
		GL.createCapabilities();
		MasterRenderer renderer = new MasterRenderer();
		
		Loader loader = new Loader();
		loader1 = loader;													// TODO FIX
		
		StaticShader shader = new StaticShader();
		shader1 = shader;													// TODO FIX
		

		// MUST PREPARE BEFORE LOADING VAO
		renderer.prepare();														
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable( GL_BLEND );
		
		HashMap<Integer, Voxel> voxels = null;
		try {
			voxels = VoxelParser.readJSON("src/resources/json/voxel-example.json", loader1);
		} catch (InvalidImageSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			
			shader.start();
			renderer.renderVoxel(voxels.get(4), 0);
			shader.stop();
			
			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
			
			
		}
	}

	public void terminate() {
		loader1.cleanUp();
		shader1.cleanUp();
		
	// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

	// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
}