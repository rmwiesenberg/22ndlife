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
    private MainMenuController mainMenuController;
    private WorldHandler worldHandler;

    private boolean mouseLocked = false;
    private DoubleBuffer mousePreLockX;
    private DoubleBuffer mousePreLockY;

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, String worldName) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);

        worldHandler = new WorldHandler(gameObjectHandler, worldName);

        mousePreLockX = BufferUtils.createDoubleBuffer(1);
        mousePreLockY = BufferUtils.createDoubleBuffer(1);

        mousePreLockX.put(renderer.getDisplayManager().getWidth() / 2);
        mousePreLockY.put(renderer.getDisplayManager().getHeight() / 2);

        mousePreLockX.rewind();
        mousePreLockY.rewind();
    }

    GameController(MasterRenderer renderer, GameObjectHandler gameObjectHandler, MainMenuController mainMenuController, File worldFile) {
        super(renderer, gameObjectHandler);

        this.mainMenuController = mainMenuController;
        pauseMenuController = new PauseMenuController(renderer, gameObjectHandler,  mainMenuController, this);

        worldHandler = new WorldHandler(gameObjectHandler, worldFile);

        mousePreLockX = BufferUtils.createDoubleBuffer(1);
        mousePreLockY = BufferUtils.createDoubleBuffer(1);

        mousePreLockX.put(renderer.getDisplayManager().getWidth() / 2);
        mousePreLockY.put(renderer.getDisplayManager().getHeight() / 2);

        mousePreLockX.rewind();
        mousePreLockY.rewind();
    }

    @Override
    public void init(){
        glfwSetCursorPos(getWindow(), mousePreLockX.get(), mousePreLockY.get());

        glfwSetKeyCallback(getWindow(), (window, key, scancode, action, mods) -> {
            Camera camera = worldHandler.getWorld().getCamera();
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
            if ( key == GLFW_KEY_W )
                camera.posSpeed(new Vector3f(1, 0, 0));
            if ( key == GLFW_KEY_S )
                camera.posSpeed(new Vector3f(-1, 0, 0));
            if ( key == GLFW_KEY_D )
                camera.posSpeed(new Vector3f(0, 1, 0));
            if ( key == GLFW_KEY_A )
                camera.posSpeed(new Vector3f(0, -1, 0));
            if ( key == GLFW_KEY_SPACE )
                camera.posSpeed(new Vector3f(0, 0, 1));
            if ( key == GLFW_KEY_C )
                camera.posSpeed(new Vector3f(0, 0, -1));
        });
    }

    @Override
    public ISceneController execute() {
        World world = worldHandler.getWorld();
        getRenderer().renderWorld(world, world.getCamera(), getRenderer().getShader());
        updateMouse();
        return this;
    }

    @Override
    public void terminate() {
        worldHandler.save();
    }

    private void updateMouse() {
        if (glfwGetMouseButton(getWindow(), GLFW_MOUSE_BUTTON_RIGHT) == GLFW_PRESS && !mouseLocked) {
            DoubleBuffer newX = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer newY = BufferUtils.createDoubleBuffer(1);

            glfwGetCursorPos(getWindow(), newX, newY);
            newX.rewind();
            newY.rewind();

            mousePreLockX = newX;
            mousePreLockY = newY;

            glfwSetInputMode(getWindow(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);

            glfwSetCursorPos(getWindow(), getDisplayManager().getWidth() / 2, getDisplayManager().getHeight() / 2);

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

            System.out.println(deltaY/getDisplayManager().getHeight());

            worldHandler.getWorld().getCamera().rotSpeed(
                    new Vector3f((float) (deltaY / getDisplayManager().getWidth()),
                            0f, (float) (-deltaX / getDisplayManager().getHeight())));

            glfwSetCursorPos(getWindow(), getDisplayManager().getWidth() / 2, getDisplayManager().getHeight() / 2);
        } else {
            return;
        }
    }

}
