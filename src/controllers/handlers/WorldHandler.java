package controllers.handlers;

import entities.block.IBlock;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldHandler {
    private GameObjectHandler gameObjectHandler;
    private File worldFile;

    private World world;

    public WorldHandler(GameObjectHandler gameObjectHandler, String worldName) {
        this.gameObjectHandler = gameObjectHandler;
        generate();
        save();
    }

    public WorldHandler(GameObjectHandler gameObjectHandler, File worldFile) {
        this.gameObjectHandler = gameObjectHandler;
        this.worldFile = worldFile;
        load();
    }

    public void save(){
        return;
    }

    private void load(){
        return;
    }

    private void generate(){
        HashMap<Integer, IBlock> blocks = gameObjectHandler.getBlocks();

        IBlock[][][] worldBlocks = new IBlock[1][1][1];
        assert blocks != null;
        worldBlocks[0][0][0] = blocks.get(6);

        Vector3f camPos = new Vector3f(0, 0, 0);
        Vector3f camRot = new Vector3f(0, 0, 0);
        Camera cam = new Camera(camPos, camRot);

        world = new World(worldBlocks, new ArrayList<>(), cam);
    }

    // Getters and Setters
    public World getWorld() {
        return world;
    }
}
