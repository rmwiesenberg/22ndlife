package controllers.handlers;

import entities.block.IBlock;
import entities.world.Camera;
import entities.world.World;
import org.joml.Vector3f;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewHandler {
    /**
     * @param world world with info to generate from
     * @param camera camera to base reference on
     * @return blocks and their world coordinates as vec3f
     */
	public static HashMap<IBlock, ArrayList<Vector3i>> getBlockView(World world, Camera camera){
        HashMap<IBlock, ArrayList<Vector3i>> blockView = new HashMap<>();
        for(int z = -1; z <= 1; z++){
            for(int y = -1; y <= 1; y++){
                for(int x = -1; x <= 1; x++){
                    Vector3i pos = new Vector3i(x, y, z);
                    IBlock curBlock = world.getBlock(pos);
                    if(!blockView.containsKey(curBlock)){
                        blockView.put(curBlock, new ArrayList<>());
                    }
                    if(!curBlock.isMT()){
                        blockView.get(curBlock).add(pos);
                    }
                }
            }
        }
        return blockView;
    }
}
