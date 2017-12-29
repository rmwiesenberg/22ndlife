package controllers.scenes;

import boundary.DisplayManager;
import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;

import static org.lwjgl.glfw.GLFW.*;

public abstract class AbsSceneController implements ISceneController {
    private MasterRenderer renderer;
    private GameObjectHandler gameObjectHandler;

    private ISceneController nextScene;

    AbsSceneController(MasterRenderer renderer, GameObjectHandler gameObjectHandler){
        this.renderer = renderer;
        this.gameObjectHandler = gameObjectHandler;
        setNextScene(this);
    }

    @Override
    public void init(){
        setupInputCallback();
        setNextScene(this);
    }

    @Override
    public void execute(){
        updateInput();
    }

    @Override
    public void terminate(){

    }

    protected void setupInputCallback() {
        glfwSetKeyCallback(renderer.getDisplayManager().getWindow(), (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
        });
    }

    protected void updateInput() {

    }

    // Getters and Setters
    GameObjectHandler getGameObjectHandler() {
        return gameObjectHandler;
    }

    MasterRenderer getRenderer() {
        return renderer;
    }

    DisplayManager getDisplayManager() { return renderer.getDisplayManager(); }

    long getWindow() { return getDisplayManager().getWindow(); }

    void setNextScene(ISceneController nextScene) { this.nextScene = nextScene; }

    @Override
    public ISceneController getNextScene() { return nextScene; }
}
