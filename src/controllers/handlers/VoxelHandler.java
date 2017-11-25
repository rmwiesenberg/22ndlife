package controllers.handlers;

import entities.Voxel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.Painter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controllers.handlers.excpetions.InvalidImageSizeException;

public class VoxelHandler {
	/**
	 * Returns a HashMap of voxels that should be loaded into memory at start.
	 * This is based on the given JSON for all voxels to be loaded into the game.
	 * 
	 * @param filepath absolute or project relative path of voxel JSON
	 * @return HashMap of voxels where their id is the key
	 * @throws InvalidImageSizeException when image does not meet voxel size expectations
	 */
	public static HashMap<Integer, Voxel> readJSON(String filepath) 
			throws InvalidImageSizeException {
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
		String voxelDir = (String) obj.get("directory");
		JSONArray voxelArr = (JSONArray) obj.get("voxel");
		
		HashMap<Integer, Voxel> voxels = new HashMap<Integer, Voxel>();
		for(int i = 0; i < voxelArr.size(); i++) {
			JSONObject curVoxel = (JSONObject) voxelArr.get(i);
			int voxelID = (int) curVoxel.get("id");
			String voxelFile = (String) curVoxel.get("filename");
			String voxelPath = voxelDir.concat(voxelFile);
			voxels.put(voxelID, makeVoxel(voxelID, voxelPath));
		}		
		return voxels;
	}
	
	/**
	 * Returns a voxel from an id and a path to the PNG of the voxel
	 * 
	 * @param voxelID integer value of the voxelID
	 * @param voxelPath absolute or project relative path of voxel PNG
	 * @return voxel representative of the inputs
	 * @throws InvalidImageSizeException when image does not meet voxel size expections
	 */
	public static Voxel makeVoxel(int voxelID, String voxelPath) 
			throws InvalidImageSizeException {
		int[][] img = ImageHandler.convertPNG(voxelPath);
		int height = img.length - 1;
		int width = img[0].length - 1;
		
		int s;
		int i;
		int[][] uv = new int[8][6];
		
		if(height == width){
			// 1x1
			for(s = 0; s < 6; s++) {
				for(i = 0; i < 4; i++) {
					uv[2*i][s] = width*(i%2);
					uv[(2*i)+1][s] = height*(i/2);
				}
			}
		} else if (height*2 == width) {
			// 2x1
			for(i = 0; i < 4; i++) {
				uv[2*i][0] = (width/2)*(i%2);
				uv[(2*i)+1][0] = height*(i/2);
			}
			for(s = 1; s < 6; s++) {
				for(i = 0; i < 4; i++) {
					uv[2*i][s] = width - (int)(width/2)*((3-i)%2);
					uv[(2*i)+1][s] = height*(i/2);
				}
			}
		} else if (height*3 == width) {
			// 3x1
			for(i = 0; i < 4; i++) {
				uv[2*i][0] = (width/3)*(i%2);
				uv[(2*i)+1][0] = height*(i/2);
			}
			for(i = 1; i < 4; i++) {
				uv[2*i][1] = (width*2/3) - (width/3)*((3-i)%2);
				uv[(2*i)+1][1] = height*(i/2);
			}
			for(s = 2; s < 6; s++) {
				for(i = 0; i < 4; i++) {
					uv[2*i][s] = width - (width/3)*((3-i)%2);
					uv[(2*i)+1][s] = height*((3-i)%2);
				}
			}
		} else if (height*3 == width*2) {
			// 3x2
			for(int h = 1; h <= 3; h++) {
				for(int w = 1; w <= 2; w++) {
					s = w * h - 1;
					for(i = 0; i < 8; i += 2) {
						uv[i][s] = (width*w/3) - (width/3)*((3-i)%2);
						uv[i+1][s] = (height*h/2) - (height/2)*((3-i)/2);
					}
				}
			}
		} else {
			throw new InvalidImageSizeException(voxelPath);
		}
		return new Voxel(voxelID, img, uv);
	}
}
