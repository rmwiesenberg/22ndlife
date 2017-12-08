package boundary.renderEngine;

import controllers.handlers.ViewHandler;
import entities.block.IBlock;
import entities.block.WorldBlock;
import entities.world.Camera;
import entities.world.World;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import entities.Voxel;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityRenderer {
	public static void renderWorld(World world, Camera camera) {
        final HashMap<IBlock, ArrayList<Vector3f>> blockView = ViewHandler.getBlockView(world, camera);
        for(IBlock block: blockView.keySet()){
            for(Vector3f vec: blockView.get(block)) {
                if(!block.isScenery()) {
                    WorldBlock wBlock = (WorldBlock) block;
                    renderWorldBlock(wBlock, vec, camera);
                } else {
                    // TODO
                }
            }
        }
	}

	public static void renderWorld(World world) {
		renderWorld(world, world.getCamera());
	}

	private static void renderWorldBlock(WorldBlock block, Vector3f vec, Camera camera){
        Voxel voxel = block.getVoxel();
        Matrix4f transformationMatrix = new Matrix4f().translate(vec).rotateXYZ(new Vector3f()).scale(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, voxel.getTexture());
        for (int s = 0; s < 6; s++) {
            renderVertexArray(voxel.getVAO(s));
        }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public static void renderVoxelSide(Voxel voxel, int side) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, voxel.getTexture());
	    GL30.glBindVertexArray(voxel.getVAO(side));
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    /**
     * Assumes texture is already selected
     * @param vao
     */
    private static void renderVertexArray(int vao){
        GL30.glBindVertexArray(vao);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
}
