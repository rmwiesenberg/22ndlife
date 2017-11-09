package boundary.core.kernel;

import boundary.core.kernel.Window;
import boundary.modules.skydome.Skydome;
import boundary.modules.terrain.Terrain;
import boundary.core.configs.Default;
import boundary.core.kernel.Camera;

/**
 * 
 * @author oreon3D
 * The RenderingEngine manages the render calls of all 3D entities
 * with shadow rendering and post processing effects
 *
 */
public class RenderingEngine {
	
	private Window window;
	
	private Skydome skydome;
	private Terrain terrain;
	
	public RenderingEngine()
	{
		window = Window.getInstance();
		skydome = new Skydome();
	}
	
	public void init()
	{
		window.init();
		terrain.init("./res/settings/terrain_settings.txt");
	}

	public void render()
	{	
		Camera.getInstance().update();
		
		Default.clearScreen();
		
		skydome.render();
		
		terrain.updateQuadtree();
		terrain.render();
		
		// draw into OpenGL window
		window.render();
	}
	
	public void update(){}
	
	public void shutdown(){}
}
