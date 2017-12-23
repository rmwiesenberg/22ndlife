package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import boundary.shaders.StaticShader;
import controllers.handlers.GameObjectHandler;

public class SplashController extends AbsSceneController {
    private MainMenuController mainMenuController;

    public SplashController(MasterRenderer renderer, StaticShader shader, GameObjectHandler gameObjectHandler) {
        super(renderer, shader, gameObjectHandler);

        mainMenuController = new MainMenuController(renderer, shader, gameObjectHandler);
    }

    @Override
    public void init(){

    }

    @Override
    public ISceneController execute() {
        return mainMenuController;
    }

    @Override
    public void terminate() {

    }

    // Getters and Setters
    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }
}
