package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;
import controllers.handlers.WorldHandler;
import entities.world.World;

import java.io.File;

public class GameController extends AbsSceneController {
    private PauseMenuController pauseMenuController;
    private MainMenuController mainMenuController;
    private WorldHandler worldHandler;

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, String worldName) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);

        worldHandler = new WorldHandler(gameObjectHandler, worldName);
    }

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, File worldFile) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);

        worldHandler = new WorldHandler(gameObjectHandler, worldFile);
    }

    @Override
    public ISceneController execute() {
        World world = worldHandler.getWorld();
        getRenderer().renderWorld(world, world.getCamera(), getRenderer().getShader());
        return this;
    }

    @Override
    public void terminate() {
        worldHandler.save();
    }

}
