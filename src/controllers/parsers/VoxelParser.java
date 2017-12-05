package controllers.parsers;

import entities.Voxel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import static java.lang.Math.toIntExact;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import boundary.renderEngine.Loader;
import controllers.parsers.exceptions.InvalidImageSizeException;

public class VoxelParser {
	private static float[][] vertices = {
			// top
			{-.5f, .5f, 0f,
			 .5f, .5f, 0f,
			 -.5f, -.5f, 0f,
			 .5f, -.5f, 0f},
			
			// bottom
			{-1f, 1f, -1f,
		     -1f, -1f, -1f,
			 1f, -1f, -1f,
			 1f, 1f, -1f},
			
			// side 1 (north)
			{1f, -1f, 1f,
			 1f, 1f, 1f,
			 1f, -1f, -1f,
			 1f, 1f, -1f},
			
			// side 2 (south)
			{-1f, -1f, 1f,
			 -1f, 1f, 1f,
			 -1f, -1f, -1f,
			 -1f, 1f, -1f},
			
			// side 3 (east)
			{-1f, 1f, 1f,
			 -1f, -1f, 1f,
			 -1f, 1f, -1f,
			 -1f, -1f, -1f},
			
			// side 4 (west)
			{1f, 1f, 1f,
			 1f, -1f, 1f,
			 1f, 1f, -1f,
			 1f, -1f, -1f},
	};
	
	private static int[] indices = {
			0, 3, 2,
			0, 1, 3,			
	}; 
	
	
	/**
	 * Returns a HashMap of voxels that should be loaded into memory at start.
	 * This is based on the given JSON for all voxels to be loaded into the game.
	 * 
	 * @param filepath absolute or project relative path of voxel JSON
	 * @return HashMap of voxels where their id is the key
	 * @throws InvalidImageSizeException when image does not meet voxel size expectations
	 */
	public static HashMap<Integer, Voxel> readJSON(String filepath, Loader loader) 
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
			voxels.put(voxelID, makeVoxel(voxelID, voxelPath, loader));
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
	private static Voxel makeVoxel(int voxelID, String voxelPath, Loader loader) 
			throws InvalidImageSizeException {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(voxelPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// transfer image and create canvas  
	    int width = image.getWidth();
	    int height = image.getHeight();
	    byte[] texture = new byte[height*width*4];
	    for (int x = 0; x < width; x++) {
	    	for (int y = 0; y < height; y++) {
	    		Color c = new Color(image.getRGB(x, y));
	    		int idx = y*width + x;
	    		texture[idx] = (byte) (c.getRed() & 0xff);
	    		texture[idx+1] = (byte) (c.getGreen() & 0xff);
	    		texture[idx+2] = (byte) (c.getBlue() & 0xff);
	    		texture[idx+3] = (byte) (c.getAlpha() & 0xff);
	    	}
	    }

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
					s = ((h-1) * 3) + (w-1);
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
		
		float[][] uvFloat = new float[6][8];
		if(height == 1) {
			if (width != height) {
				for(s = 0; s < sides; s++) {
					for(i = 0; i < idx; i++) {
						uvFloat[s][(2*i)] = (float) uv[s][(2*i)]/((float) width - 1);
					}
				}
			}
			for(s = 0; s < sides; s++) {
				for(i = 2; i < idx; i++) {
					uvFloat[s][(2*i) + 1] = 1f;
				}
			}
		} else {
			for(s = 0; s < sides; s++) {
				for(i = 0; i < idx; i++) {
					uvFloat[s][(2*i)] = (float) uv[s][(2*i)]/((float) width - 1);
					uvFloat[s][(2*i)+1] = (float) uv[s][(2*i)+1]/((float) height - 1);
				}
			}
		}
				
		int textureID = loader.loadTexture(texture, width, height);
		int[] vaoID = new int[6];
		for (s = 0; s < sides; s++) {
			vaoID[s] = loader.loadToVAO(vertices[s], indices, uvFloat[s]);
		}		
		
		return new Voxel(voxelID, textureID, vaoID);
	}
}
