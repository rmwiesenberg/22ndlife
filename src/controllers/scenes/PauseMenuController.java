package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

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
    public void init() {

    }

    @Override
    public ISceneController execute() {
        return gameController;
    }

    @Override
    public void terminate() {

    }

    // Getters and Setters
    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
