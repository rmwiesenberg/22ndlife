package controllers.handlers.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controllers.handlers.VoxelHandler;
import controllers.handlers.excpetions.InvalidImageSizeException;
import entities.Voxel;

class VoxelHandlerTest {

	@Test
	// One big, terrible test case because I interwove it a little hard
	void testVoxelHandler() {
		HashMap<Integer, Voxel> voxelMap = null;
		try {
			voxelMap = VoxelHandler.readJSON("resources/json/voxel-example.json");
		} catch (InvalidImageSizeException e) {
			e.printStackTrace();
			fail("Invalid Image Size");
		}
	}

}
