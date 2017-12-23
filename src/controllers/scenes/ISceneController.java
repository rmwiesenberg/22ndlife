package controllers.scenes;

public interface ISceneController {
    void init();
    ISceneController execute();
    void terminate();
}
