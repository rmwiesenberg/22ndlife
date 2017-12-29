package controllers.scenes;

import boundary.renderEngine.MasterRenderer;
import controllers.handlers.GameObjectHandler;
import controllers.handlers.WorldHandler;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.File;
import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class GameController extends AbsSceneController {
    private PauseMenuController pauseMenuController;
    private WorldHandler worldHandler;

    private boolean mouseLocked = false;
    private DoubleBuffer mousePreLockX;
    private DoubleBuffer mousePreLockY;

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, String worldName) {
        super(renderer, gameObjectHandler);
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);
        worldHandler = new WorldHandler(gameObjectHandler, worldName);
    }

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, File worldFile) {
        super(renderer, gameObjectHandler);
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);
        worldHandler = new WorldHandler(gameObjectHandler, worldFile);
    }

    @Override
    public void execute() {
        World world = worldHandler.getWorld();
        getRenderer().renderWorld(world, world.getCamera(), getRenderer().getShader());
        updateInput();
    }

    @Override
    public void terminate() {
        worldHandler.save();
    }

    @Override
    protected void setupInputCallback() {
        glfwSetKeyCallback(getRenderer().getDisplayManager().getWindow(), (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                setNextScene(pauseMenuController);
        });
    }

    @Override
    protected void updateInput() {
        updateCamera();
    }

    private void updateCamera() {
        // move camera
        Camera camera = worldHandler.getWorld().getCamera();
        long window = getWindow();
        if ( glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS )
            camera.moveFoward(1f);
        if ( glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS )
            camera.moveFoward(-1f);
        if ( glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS )
            camera.moveRight(1f);
        if ( glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS )
            camera.moveRight(-1f);
        if ( glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS )
            camera.moveUp(1f);
        if ( glfwGetKey(window,  GLFW_KEY_C) == GLFW_PRESS )
            camera.moveUp(-1f);

        // rotate camera
        if (glfwGetMouseButton(getWindow(), GLFW_MOUSE_BUTTON_RIGHT) == GLFW_PRESS && !mouseLocked) {
            DoubleBuffer newX = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer newY = BufferUtils.createDoubleBuffer(1);

            glfwGetCursorPos(getWindow(), newX, newY);
            newX.rewind();
            newY.rewind();

            mousePreLockX = newX;
            mousePreLockY = newY;

            glfwSetInputMode(getWindow(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);

            glfwSetCursorPos(getWindow(), getDisplayManager().getWidth() / 2,
                    getDisplayManager().getHeight() / 2);

            mouseLocked = true;
        }
        if (glfwGetMouseButton(getWindow(), GLFW_MOUSE_BUTTON_RIGHT) == GLFW_RELEASE && mouseLocked) {
            glfwSetCursorPos(getWindow(), mousePreLockX.get(), mousePreLockY.get());

            glfwSetInputMode(getWindow(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
            mouseLocked = false;
        }

        if (mouseLocked) {
            DoubleBuffer mouseNewX = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer mouseNewY = BufferUtils.createDoubleBuffer(1);

            glfwGetCursorPos(getWindow(), mouseNewX, mouseNewY);

            mouseNewX.rewind();
            mouseNewY.rewind();

            double newX = mouseNewX.get();
            double newY = mouseNewY.get();

            double deltaX = newX - getDisplayManager().getWidth()/2;
            double deltaY = newY - getDisplayManager().getHeight()/2;

            camera.moveRot(new Vector3f((float) (deltaY / getDisplayManager().getWidth()), 0f,
                    (float) (-deltaX / getDisplayManager().getHeight())));

            glfwSetCursorPos(getWindow(), getDisplayManager().getWidth() / 2,
                    getDisplayManager().getHeight() / 2);
        }

    }
}
