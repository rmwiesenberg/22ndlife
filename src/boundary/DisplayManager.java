package boundary;

import boundary.renderEngine.Loader;
import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;
import controllers.parsers.WorldObjectParser;
import controllers.parsers.exceptions.InvalidConfigurationFileException;
import entities.block.IBlock;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import boundary.shaders.StaticShader;
import controllers.parsers.VoxelParser;
import controllers.parsers.exceptions.InvalidImageSizeException;
import entities.Voxel;

import java.nio.*;
import java.util.ArrayList;
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
	private static Loader loader;
	private static StaticShader shader;
	private static MasterRenderer renderer;
	
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

		// Setup a key callback. It will be called every time a key is pressed, repeated or released
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

		// Ready rendering
        GL.createCapabilities();

        loader = new Loader();
        shader = new StaticShader();
        renderer = new MasterRenderer(WIDTH, HEIGHT, shader);

        // MUST PREPARE BEFORE LOADING VAO
        renderer.prepare();
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable( GL_BLEND );

	}

	public void loop() {
        GameObjectHandler gameObjectHandler = new GameObjectHandler(loader);
        HashMap<Integer, IBlock> blocks = gameObjectHandler.getBlocks();
        
        IBlock[][][] worldBlocks = new IBlock[1][1][1];
		assert blocks != null;
		worldBlocks[0][0][0] = blocks.get(6);

        Vector3f camPos = new Vector3f(0, 0, 0);
        Vector3f camRot = new Vector3f(0, 0, 0);
        Camera cam = new Camera(camPos, camRot);

        World world = new World(worldBlocks, new ArrayList<>(), cam);

        // Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			
			shader.start();
			renderer.renderWorld(world, world.getCamera(), shader);
			shader.stop();
			
			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
			
			
		}
	}

	public void swapBuffers(){
	    glfwSwapBuffers(window);
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

	public void terminate() {
		loader.cleanUp();
		shader.cleanUp();
		
	// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

	// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	public boolean shouldWindowClose(){
		return glfwWindowShouldClose(window);
	}

	// Getters and Setters
    public int getWidth(){
	    return WIDTH;
    }

    public int getHeight(){
	    return HEIGHT;
    }
}