package controllers;

import boundary.DisplayManager;
import boundary.renderEngine.Loader;
import boundary.renderEngine.MasterRenderer;
import boundary.shaders.StaticShader;
import controllers.handlers.GameObjectHandler;
import controllers.scenes.ISceneController;
import controllers.scenes.SplashController;

public class MainStateController {
    private ISceneController currentScene;
    private DisplayManager displayManager;
    private GameObjectHandler gameObjectHandler;

    private static Loader loader;
    private static MasterRenderer renderer;

    public MainStateController(DisplayManager displayManager){
        this.displayManager = displayManager;
        loader = new Loader();
        this.gameObjectHandler = new GameObjectHandler(loader);
    }

    public void init(){
        renderer = new MasterRenderer(displayManager, new StaticShader());
        currentScene = new SplashController(renderer, gameObjectHandler);
        renderer.prepare();
    }

    public void render(){
        renderCurrentScene();
        displayManager.swapBuffers();
    }

    public void terminate(){
        loader.cleanUp();
        renderer.cleanUp();
    }

    private void renderCurrentScene(){
        renderer.startShader();
        ISceneController newScene = currentScene.execute();

        // check if scene returns new scene and cleanup if needed
        if (newScene != currentScene) {
            currentScene.terminate();
            currentScene = newScene;
            currentScene.init();
        }
        renderer.stopShader();
    }
}
