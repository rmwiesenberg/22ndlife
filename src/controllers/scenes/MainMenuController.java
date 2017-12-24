package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

public class MainMenuController extends AbsSceneController {
    public MainMenuController(MasterRenderer renderer, GameObjectHandler gameObjectHandler) {
        super(renderer, gameObjectHandler);
    }

    @Override
    public ISceneController execute() {
        return new GameController(getRenderer(), getGameObjectHandler(), "test");
    }
}
