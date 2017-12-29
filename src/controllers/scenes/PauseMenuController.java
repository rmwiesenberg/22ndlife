package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

public class PauseMenuController extends AbsSceneController {
    private MainMenuController mainMenuController;
    private GameController gameController;

    PauseMenuController(MasterRenderer renderer, GameObjectHandler gameObjectHandler,
                        MainMenuController mainMenuController, GameController gameController) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        this.gameController = gameController;
    }

    @Override
    protected void setupInputCallback() {
        glfwSetKeyCallback(getRenderer().getDisplayManager().getWindow(),
                (window, key, scancode, action, mods) -> {
                    if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                        setNextScene(gameController);
                });
    }
}
