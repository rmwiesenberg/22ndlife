package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

public class MainMenuController extends AbsSceneController {
    MainMenuController(MasterRenderer renderer, GameObjectHandler gameObjectHandler) {
        super(renderer, gameObjectHandler);
    }

    @Override
    public void execute() {
        setNextScene(new GameController(getRenderer(), getGameObjectHandler(), this, "test"));
    }
}
