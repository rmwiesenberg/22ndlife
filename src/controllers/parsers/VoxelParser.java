package controllers.parsers;

import boundary.renderEngine.Loader;
import controllers.parsers.exceptions.InvalidImageSizeException;
import entities.Voxel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Math.toIntExact;

public class VoxelParser {
	private static float[][] vertices = {
	        // coordinates are in camera frame
			// top
			{
					0.5f, -0.5f, -0.5f,
					0.5f, 0.5f, -0.5f,
					-0.5f, 0.5f, -0.5f,
					-0.5f, -0.5f, -0.5f,
			},
            // bottom
			{
					-0.5f, 0.5f, 0.5f,
					-0.5f, -0.5f, 0.5f,
					0.5f, -0.5f, 0.5f,
					0.5f, 0.5f, 0.5f,
			},
            {
                    0.5f, -0.5f, -0.5f,
                    0.5f, 0.5f, -0.5f,
                    0.5f, 0.5f, 0.5f,
                    0.5f, -0.5f, 0.5f,
            },
            {
                    -0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f,
                    0.5f, -0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f
            },
            {
                    -0.5f, 0.5f, -0.5f,
                    -0.5f, -0.5f, -0.5f,
                    -0.5f, -0.5f, 0.5f,
                    -0.5f, 0.5f, 0.5f,
            },

			{
			        0.5f, 0.5f, -0.5f,
			        -0.5f, 0.5f, -0.5f,
                    -0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, 0.5f,
			},
	};
	
	private static int[] indices = {
			0, 1, 2,
			2, 3, 0,			
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
	    boolean hasAlpha = image.getColorModel().hasAlpha();
	    byte[] texture = new byte[height*width*4];
	    for (int x = 0; x < width; x++) {
	    	for (int y = 0; y < height; y++) {
	    		int argb = image.getRGB(x, y);
	    		int idx = (y*width + x)*4;
	    		texture[idx] = (byte) ((argb >> 16) & 0xff);
	    		texture[idx+1] = (byte) ((argb >> 8) & 0xff);
	    		texture[idx+2] = (byte) ((argb >> 0) & 0xff);
	    		if (hasAlpha) {
	    			texture[idx+3] = (byte) ((argb >> 24) & 0xff);
	    		} else {
	    			texture[idx+3] = (byte) 255;
	    		}
	    	}
	    }

		int sides = 6;
		int idx = 4;
		
		int s;
		int i;
		float[][] uv = new float[6][8];
		
		if(height == width){
			// 1x1
			
			// sides
			for(s = 0; s < sides; s++) {
				uv[s][0] = 0f;
				uv[s][1] = 0f;
				uv[s][2] = 1f;
				uv[s][3] = 0f;
				uv[s][4] = 0f;
				uv[s][5] = 1f;
				uv[s][6] = 1f;
				uv[s][7] = 1f;
			}
		} else if (height*2 == width) {
			// 2x1
			
			// top
			uv[0][0] = 0f;
			uv[0][1] = 0f;
			uv[0][2] = .5f;
			uv[0][3] = 0f;
			uv[0][4] = 0f;
			uv[0][5] = 1f;
			uv[0][6] = .5f;
			uv[0][7] = 1f;
			
			// sides
			for(s = 1; s < sides; s++) {
				uv[s][0] = .5f;
				uv[s][1] = 0f;
				uv[s][2] = 1f;
				uv[s][3] = 0f;
				uv[s][4] = .5f;
				uv[s][5] = 1f;
				uv[s][6] = 1f;
				uv[s][7] = 1f;
			}
		} else if (height*3 == width) {
			// 3x1
			
			// top
			uv[0][0] = 0f;
			uv[0][1] = 0f;
			uv[0][2] = (float) 1/3;
			uv[0][3] = 0f;
			uv[0][4] = 0f;
			uv[0][5] = 1f;
			uv[0][6] = (float) 1/3;
			uv[0][7] = 1f;
			
			// bottom
			uv[1][0] = (float) 1/3;
			uv[1][1] = 0f;
			uv[1][2] = (float) 2/3;
			uv[1][3] = 0f;
			uv[1][4] = (float) 1/3;
			uv[1][5] = 1f;
			uv[1][6] = (float) 2/3;
			uv[1][7] = 1f;
			
			//sides
			for(s = 2; s < sides; s++) {
				uv[s][0] = (float) 2/3;
				uv[s][1] = 0f;
				uv[s][2] = 1f;
				uv[s][3] = 0f;
				uv[s][4] = (float) 2/3;
				uv[s][5] = 1f;
				uv[s][6] = 1f;
				uv[s][7] = 1f;	
			}
		} else if (height*3 == width*2) {
			// 3x2
			// all sides competently
			for(int h = 0; h < 2; h++) {
				for(int w = 0; w < 3; w++) {
					s = (h*3) + w;
					uv[s][0] = ((float) w/3);
					uv[s][1] = ((float) h/2);
					uv[s][2] = ((float) (w+1)/3);
					uv[s][3] = ((float) h/2);
					uv[s][4] = ((float) (w+1)/3);
					uv[s][5] = ((float) (h+1)/2);
					uv[s][6] = ((float) w/3);
					uv[s][7] = ((float) (h+1)/2);
				}
			}
		} else {
			throw new InvalidImageSizeException(voxelPath);
		}
				
		int textureID = loader.loadTexture(texture, width, height);
		int[] vaoID = new int[6];
		for (s = 0; s < sides; s++) {
			vaoID[s] = loader.loadToVAO(vertices[s], indices, uv[s]);
		}		
		
		return new Voxel(voxelID, textureID, vaoID);
	}
}
