package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import boundary.shaders.StaticShader;
import controllers.handlers.GameObjectHandler;
import controllers.scenes.AbsSceneController;

public class PauseMenuController extends AbsSceneController {
    private MainMenuController mainMenuController;
    private GameController gameController;

    PauseMenuController(MasterRenderer renderer, StaticShader shader, GameObjectHandler gameObjectHandler,
                        MainMenuController mainMenuController, GameController gameController) {
        super(renderer, shader, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        this.gameController = gameController;
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

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
