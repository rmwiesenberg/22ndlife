package controllers.handlers;

import entities.block.IBlock;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewHandler {
    /**
     * @param blocks list of block to consider
     * @param camera camera to evaluate at
     * @return blocks and their world coordinates as vec3f
     */
    private static HashMap<IBlock, ArrayList<Vector3f>> getBlockView(IBlock[][][] blocks, Camera camera){
        HashMap<IBlock, ArrayList<Vector3f>> blockView = new HashMap<>();
        int zSize = blocks.length;
        int ySize = blocks[0].length;
        int xSize = blocks[0][0].length;
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                for(int z = 0; z < zSize; z++){
                    IBlock curBlock = blocks[x][y][z];
                    if(curBlock.isMT()){
                        continue;
                    }
                    if(!blockView.containsKey(curBlock)){
                        blockView.put(curBlock, new ArrayList<>());
                    }
                    blockView.get(curBlock).add(new Vector3f(x, y, z));
                }
            }
        }
        return blockView;
	}

    /**
     * @param world world with info to generate from
     * @param camera camera to base reference on
     * @return blocks and their world coordinates as vec3f
     */
	public static HashMap<IBlock, ArrayList<Vector3f>> getBlockView(World world, Camera camera){
	    return getBlockView(world.getBlocks(), camera);
    }
}
