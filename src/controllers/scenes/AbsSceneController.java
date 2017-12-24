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
        glfwSetKeyCallback(renderer.getDisplayManager().getWindow(),
                (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
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
