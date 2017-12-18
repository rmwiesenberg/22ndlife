package boundary.renderEngine;

import boundary.shaders.StaticShader;
import controllers.handlers.ViewHandler;
import entities.Voxel;
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

import java.util.ArrayList;
import java.util.HashMap;

public class EntityRenderer {
	public static void renderWorld(World world, Camera camera, StaticShader shader) {
        final HashMap<IBlock, ArrayList<Vector3f>> blockView = ViewHandler.getBlockView(world, camera);
        for(IBlock block: blockView.keySet()){
            for(Vector3f vec: blockView.get(block)) {
                if(!block.isScenery()) {
                    WorldBlock wBlock = (WorldBlock) block;
                    renderWorldBlock(wBlock, vec, camera, shader);
                } else {
                    // TODO
                }
            }
        }
	}

	public static void renderWorld(World world, StaticShader shader) {
		renderWorld(world, world.getCamera(), shader);
	}

	private static void renderWorldBlock(WorldBlock block, Vector3f vec, Camera camera, StaticShader shader){
        Voxel voxel = block.getVoxel();
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, voxel.getTexture());
        Matrix4f transformationMatrix = new Matrix4f().translate(vec.add(0,0, 0)).rotateXYZ(new Vector3f()).scale(1f);
        shader.loadTransformationMatrix(transformationMatrix);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, voxel.getTexture());
        for (int s = 0; s < 6; s++) {
            renderVertexArray(shader, voxel.getVAO(s), transformationMatrix);
        }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public static void renderVoxelSide(Voxel voxel, int side, StaticShader shader){
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
     * @param shader
     * @param vao
     * @param transformationMatrix
     */
    private static void renderVertexArray(StaticShader shader, int vao, Matrix4f transformationMatrix){
        GL30.glBindVertexArray(vao);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        shader.loadTransformationMatrix(transformationMatrix);
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
}
