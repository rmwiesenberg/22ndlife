package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

import static org.lwjgl.glfw.GLFW.*;

public abstract class AbsSceneController implements ISceneController {
    private MasterRenderer renderer;
    private GameObjectHandler gameObjectHandler;

    AbsSceneController(MasterRenderer renderer, GameObjectHandler gameObjectHandler){
        this.renderer = renderer;
        this.gameObjectHandler = gameObjectHandler;
    }

    @Override
    public void init(){
        // Setup a key callback. It will be called every time a key is pressed, repeated or released
        glfwSetKeyCallback(renderer.getDisplayManager().getWindow(),
                (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
    }

    @Override
    public void terminate(){

    }

    // Getters and Setters
    public GameObjectHandler getGameObjectHandler() {
        return gameObjectHandler;
    }

    public MasterRenderer getRenderer() {
        return renderer;
    }
}
