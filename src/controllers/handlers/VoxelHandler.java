package controllers.handlers;

import entities.Voxel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controllers.handlers.excpetions.InvalidImageSizeException;

public class VoxelHandler {
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
	
	public static Voxel makeVoxel(int voxelID, String voxelPath) 
			throws InvalidImageSizeException {
		int[][] img = ImageHandler.convertPNG(voxelPath);
		int height = img.length;
		int width = img[0].length;
		
		if(height == width){
			return new Voxel(voxelID, img);
		} else if (height*2 == width){
			return new Voxel(voxelID, 
					Arrays.copyOfRange(img, 0, height),
					Arrays.copyOfRange(img, height, width)); 
		} else if (height*3 == width) {
			return new Voxel(voxelID, 
					Arrays.copyOfRange(img, 0, height),
					Arrays.copyOfRange(img, height, height*2), 
					Arrays.copyOfRange(img, height, width));
		} else if (height*3 == width*2) {
			int[][] tHalf = new int[height/2][width];
			int[][] bHalf = new int[height/2][width];
			for(int w = 0; w < width; w++) {
				for(int h = 0; h < height; h++) {
					if(h < height/2) {
						tHalf[h][w] = img[h][w];
					} else {
						bHalf[h-height/2][w] = img[h][w];
					}
				}
			}
			return new Voxel(voxelID,
					Arrays.copyOfRange(tHalf, 0, height),
					Arrays.copyOfRange(tHalf, height, height*2), 
					Arrays.copyOfRange(tHalf, height, width),
					Arrays.copyOfRange(bHalf, 0, height),
					Arrays.copyOfRange(bHalf, height, height*2), 
					Arrays.copyOfRange(bHalf, height, width));			
		} else {
			throw new InvalidImageSizeException(voxelPath);
		}
	}
}
