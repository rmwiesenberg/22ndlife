package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import boundary.shaders.StaticShader;
import controllers.handlers.GameObjectHandler;

public abstract class AbsSceneController implements ISceneController {
    private StaticShader shader;
    private MasterRenderer renderer;
    private GameObjectHandler gameObjectHandler;

    AbsSceneController(MasterRenderer renderer, StaticShader shader, GameObjectHandler gameObjectHandler){
        this.renderer = renderer;
        this.shader = shader;
        this.gameObjectHandler = gameObjectHandler;
    }

    public StaticShader getShader() {
        return shader;
    }

    public GameObjectHandler getGameObjectHandler() {
        return gameObjectHandler;
    }

    public MasterRenderer getRenderer() {
        return renderer;
    }
}
