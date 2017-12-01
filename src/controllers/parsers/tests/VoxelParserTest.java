package controllers.parsers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.Test;

import controllers.parsers.VoxelParser;
import controllers.parsers.exceptions.InvalidImageSizeException;
import entities.Voxel;

class VoxelParserTest {

	@Test
	// One big, terrible test case because I interwove it a little hard
	void testVoxelHandler() {
		HashMap<Integer, Voxel> voxelMap = null;
		try {
			voxelMap = VoxelParser.readJSON("src/resources/json/voxel-example.json");
		} catch (InvalidImageSizeException e) {
			e.printStackTrace();
			fail("Invalid Image Size");
		}
		
		Set<Integer> keys = voxelMap.keySet();
		
		assertTrue(keys.contains(0));
		assertTrue(keys.contains(1));
		assertTrue(keys.contains(2));
		assertTrue(keys.contains(3));
		assertTrue(keys.contains(4));
		assertTrue(keys.contains(5));
		assertEquals(keys.size(), 6);
		
		for(Integer key: keys) {
			Voxel curVoxel = voxelMap.get(key);
			byte[] tex = curVoxel.getTexture();
			for(int s = 0; s < 6; s++) {
				int[] uv = curVoxel.getSideUV(s);
				// check box is correct
				assertEquals(uv[0], uv[4]);
				assertEquals(uv[1], uv[3]);
				assertEquals(uv[2], uv[6]);
				assertEquals(uv[5], uv[7]);
				
				// check box is all same color
				int color = tex[uv[1]][uv[0]];
				for(int w = uv[0]; w <= uv[6]; w++) {
					for(int h = uv[1]; h <= uv[7]; h++) {
						assertEquals(tex[h][w], color);
					}
				}
			}
		}
	}

}
