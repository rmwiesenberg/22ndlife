package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import boundary.shaders.StaticShader;
import controllers.handlers.GameObjectHandler;

public class MainMenuController extends AbsSceneController {


    MainMenuController(MasterRenderer renderer, StaticShader shader, GameObjectHandler gameObjectHandler) {
        super(renderer, shader, gameObjectHandler);
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
