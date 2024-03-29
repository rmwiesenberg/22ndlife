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

        IBlock[][][] worldBlocks = new IBlock[1][3][3];
        assert blocks != null;

        int zMax = worldBlocks.length;
        int yMax = worldBlocks[0].length;
        int xMax = worldBlocks[0][0].length;

        for(int z = 0; z < zMax; z++){
            for(int y = 0; y < yMax; y++){
                for(int x = 0; x < xMax; x++){
                    worldBlocks[z][y][x] = blocks.get(6);
                }
            }
        }

        Vector3f camPos = new Vector3f(5, -5, 7);
        Vector3f camRot = new Vector3f((float) Math.toRadians(135),
                                       (float) Math.toRadians(0),
                                       (float) Math.toRadians(-45));
        Camera cam = new Camera(camPos, camRot);

        world = new World(worldBlocks, new ArrayList<>(), cam);
    }

    // Getters and Setters
    public World getWorld() {
        return world;
    }
}
