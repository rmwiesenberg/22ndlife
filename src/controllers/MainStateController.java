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
    private static StaticShader shader;
    private static MasterRenderer renderer;

    public MainStateController(DisplayManager displayManager){
        this.displayManager = displayManager;
        this.gameObjectHandler = new GameObjectHandler(loader);
    }

    public void init(){
        loader = new Loader();
        shader = new StaticShader();
        renderer = new MasterRenderer(displayManager.getWidth(),
                                      displayManager.getHeight(),
                                      shader);

        currentScene = new SplashController(renderer, shader, gameObjectHandler);
    }

    public void render(){
        renderCurrentScene();
        displayManager.swapBuffers();
    }

    public void terminate(){

    }

    private void renderCurrentScene(){
        shader.start();
        ISceneController newScene = currentScene.execute();

        // check if scene returns new scene and cleanup if needed
        if (newScene != currentScene) {
            currentScene.terminate();
            currentScene = newScene;
            currentScene.init();
        }
        shader.stop();
    }
}
