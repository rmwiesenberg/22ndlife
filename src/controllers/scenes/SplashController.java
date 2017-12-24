package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

public class SplashController extends AbsSceneController {
    private MainMenuController mainMenuController;

    public SplashController(MasterRenderer renderer, GameObjectHandler gameObjectHandler) {
        super(renderer, gameObjectHandler);

        mainMenuController = new MainMenuController(renderer, gameObjectHandler);
    }

    @Override
    public ISceneController execute() {
        return mainMenuController;
    }

    // Getters and Setters
    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
}
