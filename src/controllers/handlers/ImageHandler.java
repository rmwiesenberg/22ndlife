package controllers.handlers;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	/**
	 * Returns the float array derived from a single integer
	 * 
	 * 
	 * @param color [blue, green, red, alpha] bitwise added to the int
	 * @return float array of 0..1
	 */
	public static float[] convertInt2FloatArray(int color) {		
		float[] output = new float[4];
		for(int i = 0; i < 4; i++) {
			output[i] = ((float) ((color >> 8*i) & 0xff)) / 255.0f;
		}
		return output;
	}
	
	/**
	 * Returns an int array array with color values bitwise added into an int
	 * [blue, green, red, alpha] with the indexes representing int[y][x] 
	 * 
	 * @param filepath absolute or project relative path of image
	 * @return the int array
	 */
	public static int[][] convertPNG(String filepath) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// transfer image and create canvas
	    final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	    final int width = image.getWidth();
	    final int height = image.getHeight();
	    final boolean hasAlphaChannel = image.getAlphaRaster() != null;
	
	    // handle alpha channel and int parsing
	    int[][] result = new int[height][width];
	    if (hasAlphaChannel) {
	       final int pixelLength = 4;
	       for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	          int argb = 0;
	          argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
	          argb += ((int) pixels[pixel + 1] & 0xff); // blue
	          argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
	          argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
	          result[row][col] = argb;
	          col++;
	          if (col == width) {
	             col = 0;
	             row++;
	          }
	       }
	    } else {
	       final int pixelLength = 3;
	       for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
	          int argb = 0;
	          argb += -16777216; // 255 alpha
	          argb += ((int) pixels[pixel] & 0xff); // blue
	          argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
	          argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
	          result[row][col] = argb;
	          col++;
	          if (col == width) {
	             col = 0;
	             row++;
	          }
	       }
	    }
	    
	    return result;
	}
}
