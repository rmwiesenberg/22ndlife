package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;
import controllers.handlers.WorldHandler;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;

import java.io.File;

import static org.lwjgl.glfw.GLFW.*;

public class GameController extends AbsSceneController {
    private PauseMenuController pauseMenuController;
    private MainMenuController mainMenuController;
    private WorldHandler worldHandler;

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, String worldName) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);

        worldHandler = new WorldHandler(gameObjectHandler, worldName);
    }

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, File worldFile) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);

        worldHandler = new WorldHandler(gameObjectHandler, worldFile);
    }

    @Override
    public void init(){
        glfwSetKeyCallback(getRenderer().getDisplayManager().getWindow(),
            (window, key, scancode, action, mods) -> {
                Camera camera = worldHandler.getWorld().getCamera();
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, true);
                else if ( key == GLFW_KEY_W )
                    camera.moveSpeed(new Vector3f(1, 0, 0));
                else if ( key == GLFW_KEY_S )
                    camera.moveSpeed(new Vector3f(-1, 0, 0));
                else if ( key == GLFW_KEY_D )
                    camera.moveSpeed(new Vector3f(0, 1, 0));
                else if ( key == GLFW_KEY_A )
                    camera.moveSpeed(new Vector3f(0, -1, 0));
                else if ( key == GLFW_KEY_SPACE )
                    camera.moveSpeed(new Vector3f(0, 0, 1));
                else if ( key == GLFW_KEY_LEFT_CONTROL )
                    camera.moveSpeed(new Vector3f(0, 0, -1));
        });
    }

    @Override
    public ISceneController execute() {
        World world = worldHandler.getWorld();
        getRenderer().renderWorld(world, world.getCamera(), getRenderer().getShader());
        return this;
    }

    @Override
    public void terminate() {
        worldHandler.save();
    }

}
