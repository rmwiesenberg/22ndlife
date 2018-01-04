package controllers.handlers;

import entities.block.IBlock;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;
import org.joml.Vector3i;

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
        assert blocks != null;

        Vector3f camPos = new Vector3f(5, -5, 7);
        Vector3f camRot = new Vector3f((float) Math.toRadians(135),
                                       (float) Math.toRadians(0),
                                       (float) Math.toRadians(-45));
        Camera cam = new Camera(camPos, camRot);

        world = new World(new HashMap<>(), new ArrayList<>(), cam);

        world.addBlock(blocks.get(6), new Vector3i(-1, 1, -1));
        world.addBlock(blocks.get(6), new Vector3i(0, 0, 0));
        world.addBlock(blocks.get(6), new Vector3i(-1, 0, 0));
        world.addBlock(blocks.get(6), new Vector3i(0, 1, 0));
        world.addBlock(blocks.get(6), new Vector3i(1, 1, 1));
        world.addBlock(blocks.get(6), new Vector3i(-1, -1, 1));
        world.addBlock(blocks.get(6), new Vector3i(1, 0, 1));
        world.addBlock(blocks.get(6), new Vector3i(1, -1, 1));;
        world.addBlock(blocks.get(6), new Vector3i(0, -1, 1));
    }

    // Getters and Setters
    public World getWorld() {
        return world;
    }
}
