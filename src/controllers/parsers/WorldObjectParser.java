package controllers.parsers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import static java.lang.Math.toIntExact;

import entities.Voxel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controllers.parsers.exceptions.InvalidConfigurationFileException;
import entities.block.IBlock;
import entities.block.WorldBlock;

public class WorldObjectParser {
	private HashMap<Integer, Voxel> voxels;
	private String voxelPath;

	public WorldObjectParser(HashMap<Integer, Voxel> voxels, String voxelPath){
        this.voxels = voxels;
	    this.voxelPath = voxelPath;
    }

	/**
	 * Returns a HashMap of voxels that should be loaded into memory at start.
	 * This is based on the given JSON for all voxels to be loaded into the game.
	 * Verifies voxel path against world config
	 * 
	 * @param filepath absolute or project relative path of block JSON
	 * @return Hash of blocks in JSON
	 * @throws InvalidConfigurationFileException thrown with incorrect voxel path
	 */
	public HashMap<Integer, IBlock> readWorldBlockJSON(String filepath)
			throws InvalidConfigurationFileException {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String voxelDir = (String) obj.get("voxel");
		if(!voxelDir.matches(voxelPath)) {
			throw new InvalidConfigurationFileException();
		}
		JSONArray blockArr = (JSONArray) obj.get("block");
		
		HashMap<Integer, IBlock> blocks = new HashMap<Integer, IBlock>();
		for(int i = 0; i < blockArr.size(); i++) {
			JSONObject curBlock = (JSONObject) blockArr.get(i);
			int blockID = toIntExact((long) curBlock.get("id"));
			String blockName = (String) curBlock.get("name");
			int voxelID = toIntExact((long) curBlock.get("voxelID"));
			blocks.put(blockID, new WorldBlock(blockID, blockName, voxels.get(voxelID)));
		}
		return blocks;
	}

	// Getters and Setters
    public String getVoxelPath() {
        return voxelPath;
    }

    public HashMap<Integer, Voxel> getVoxels() {
        return voxels;
    }
}
