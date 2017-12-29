package controllers.scenes;

public interface ISceneController {

    /**
     * Runs once, before first execute
     */
    void init();

    /**
     * Runs every frame
     */
    void execute();

    /**
     * Runs after last execute
     */
    void terminate();

    /**
     *
     * @return nextScene
     */
    ISceneController getNextScene();
}