package controllers.handlers;

import boundary.renderEngine.Loader;
import controllers.parsers.VoxelParser;
import controllers.parsers.WorldObjectParser;
import controllers.parsers.exceptions.InvalidConfigurationFileException;
import controllers.parsers.exceptions.InvalidImageSizeException;
import entities.Voxel;
import entities.block.IBlock;

import java.util.HashMap;

public class GameObjectHandler {
    HashMap<Integer, Voxel> voxels;
    HashMap<Integer, IBlock> blocks;

    public GameObjectHandler(Loader loader) {
        String voxelPath = "resources/json/voxel-example.json";
        try {
            voxels = VoxelParser.readJSON(voxelPath, loader);
        } catch (InvalidImageSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        WorldObjectParser wParser = new WorldObjectParser(voxels, voxelPath);

        String worldObjectPath = "resources/json/block-example.json";
        try {
            blocks = wParser.readWorldBlockJSON(worldObjectPath);
        } catch (InvalidConfigurationFileException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public HashMap<Integer, IBlock> getBlocks() {
        return blocks;
    }

    public HashMap<Integer, Voxel> getVoxels() {
        return voxels;
    }
}
