package boundary.modules.terrain;

import boundary.core.buffers.PatchVBO;
import boundary.core.math.Vec2f;
import boundary.core.math.Vec3f;
import boundary.core.scene.Node;

public class TerrainQuadtree extends Node {
	
	private static int rootNodes = 8;
	
	public TerrainQuadtree(TerrainConfig config) {
		
		PatchVBO buffer = new PatchVBO();
		buffer.allocate(generatePatch());
		
		for (int i = 0; i < rootNodes; i++) {
			for (int j = 0; j < rootNodes; j++) {
				addChild(new TerrainNode(buffer, config, new Vec2f(i/(float) rootNodes, j/(float) rootNodes),0,new Vec2f(i,j)));
			}
		}
		
		getWorldTransform().setScaling(new Vec3f(config.getScaleXZ(), config.getScaleY(), config.getScaleXZ()));
		getWorldTransform().setTranslation(new Vec3f(config.getScaleXZ()/2f,0,config.getScaleXZ()/2));
	}
	
	public void updateQuadtree() {
		for(Node child : getChildren()) {
			((TerrainNode) child).updateQuadtree();
		}
	}
	
	public Vec2f[] generatePatch() {
		
		Vec2f[] verticies = new Vec2f[16];
		
		int index = 0;
		
		verticies[index++] = new Vec2f(0,0);
		verticies[index++] = new Vec2f(0.333f,0);
		verticies[index++] = new Vec2f(0.666f,0);
		verticies[index++] = new Vec2f(1,0);

		verticies[index++] = new Vec2f(0,0.333f);
		verticies[index++] = new Vec2f(0.333f,0.333f);
		verticies[index++] = new Vec2f(0.666f,0.333f);
		verticies[index++] = new Vec2f(1,0.333f);
		
		verticies[index++] = new Vec2f(0,0.666f);
		verticies[index++] = new Vec2f(0.333f,0.666f);
		verticies[index++] = new Vec2f(0.666f,0.666f);
		verticies[index++] = new Vec2f(1,0.666f);

		verticies[index++] = new Vec2f(0,1);
		verticies[index++] = new Vec2f(0.333f,1);
		verticies[index++] = new Vec2f(0.666f,1);
		verticies[index++] = new Vec2f(1,1);
		
		return verticies;
		
		
	}

	public static int getRootNodes() {
		return rootNodes;
	}

	public static void setRootNodes(int rootNodes) {
		TerrainQuadtree.rootNodes = rootNodes;
	}
}
