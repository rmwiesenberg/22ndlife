package controllers.parsers;

import entities.Voxel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import static java.lang.Math.toIntExact;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controllers.parsers.exceptions.InvalidImageSizeException;

public class VoxelParser {
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
			int voxelID = toIntExact((long) curVoxel.get("id"));
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
	private static Voxel makeVoxel(int voxelID, String voxelPath) 
			throws InvalidImageSizeException {
		int[][] img = ImageParser.convertPNG(voxelPath);
		int height = img.length;
		int width = img[0].length;
		
		int sides = 6;
		int idx = 4;
		
		int s;
		int i;
		int[][] uv = new int[6][8];
		
		if(height == width){
			// 1x1
			for(s = 0; s < sides; s++) {
				for(i = 0; i < idx; i++) {
					uv[s][(2*i)] = width*(i%2);
					uv[s][(2*i)+1] = height*(i/2);
				}
			}
		} else if (height*2 == width) {
			// 2x1
			for(i = 0; i < idx; i++) {
				uv[0][(2*i)] = (width/2)*(i%2);
				uv[0][(2*i)+1] = height*(i/2);
			}
			for(s = 1; s < sides; s++) {
				for(i = 0; i < idx; i++) {
					uv[s][(2*i)] = width - (int)(width/2)*((3-i)%2);
					uv[s][(2*i)+1] = height*(i/2);
				}
			}
		} else if (height*3 == width) {
			// 3x1
			for(i = 0; i < idx; i++) {
				uv[0][(2*i)] = (width/3)*(i%2);
				uv[0][(2*i)+1] = height*(i/2);
			}
			for(i = 0; i < idx; i++) {
				uv[1][(2*i)] = (width*2/3) - (width/3)*((3-i)%2);
				uv[1][(2*i)+1] = height*(i/2);
			}			
			for(s = 2; s < sides; s++) {
				for(i = 0; i < idx; i++) {
					uv[s][(2*i)] = width - (width/3)*((3-i)%2);
					uv[s][(2*i)+1] = height*(i/2);
				}
			}
		} else if (height*3 == width*2) {
			// 3x2
			for(int h = 1; h <= 2; h++) {
				for(int w = 1; w <= 3; w++) {
					s = w * h - 1;
					for(i = 0; i < idx; i++) {
						uv[s][(2*i)] = (width*w/3) - (width/3)*((3-i)%2);
						uv[s][(2*i)+1] = (height*h/2) - (height/2)*((3-i)/2);
					}
				}
			}
		} else {
			throw new InvalidImageSizeException(voxelPath);
		}
		
		// adjust for zero indexing and tex bounds
		for(s = 0; s < sides; s++) {
			uv[s][2]--;
			uv[s][5]--;
			uv[s][6]--;
			uv[s][7]--;
		}
		
		return new Voxel(voxelID, img, uv);
	}
}
