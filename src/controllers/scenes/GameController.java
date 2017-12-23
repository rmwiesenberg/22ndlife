package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import boundary.shaders.StaticShader;
import controllers.handlers.GameObjectHandler;
import controllers.scenes.AbsSceneController;

public class GameController extends AbsSceneController {
    private PauseMenuController pauseMenuController;
    private MainMenuController mainMenuController;

    GameController(MasterRenderer renderer, StaticShader shader, GameObjectHandler gameObjectHandler) {
        super(renderer, shader, gameObjectHandler);

        pauseMenuController = new PauseMenuController(renderer, shader, gameObjectHandler,
                mainMenuController, this);
    }

    @Override
    public void init() {

    }

    @Override
    public ISceneController execute() {
        return null;
    }

    @Override
    public void terminate() {

    }
}
