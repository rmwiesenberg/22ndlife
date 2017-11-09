package boundary.modules.terrain;

import boundary.core.kernel.Camera;
import boundary.core.scene.GameObject;
import boundary.core.shaders.Shader;
import boundary.core.utils.ResourceLoader;

public class TerrainShader extends Shader{
	
	private static TerrainShader instance = null;
	
	public static TerrainShader getInstance(){
		
		if (instance == null){
			
			instance = new TerrainShader();
		}
		return instance;
	}
	
	protected TerrainShader(){
		
		super();
		
		addVertexShader(ResourceLoader.loadShader("shaders/terrain/terrain_VS.glsl"));
		addTessellationControlShader(ResourceLoader.loadShader("shaders/terrain/terrain_TC.glsl"));
		addTessellationEvaluationShader(ResourceLoader.loadShader("shaders/terrain/terrain_TE.glsl"));
		addGeometryShader(ResourceLoader.loadShader("shaders/terrain/terrain_GS.glsl"));
		addFragmentShader(ResourceLoader.loadShader("shaders/terrain/terrain_FS.glsl"));
		
		compileShader();
		
		addUniform("localMatrix");
		addUniform("worldMatrix");
		addUniform("m_ViewProjection");
		
		addUniform("index");
		addUniform("gap");
		addUniform("lod");
		addUniform("scaleY");
		addUniform("location");
		addUniform("cameraPosition");
		
		for (int i=0; i<8; i++){
			addUniform("lod_morph_area[" + i + "]");
		}
	}
	
	public void updateUniforms(GameObject object) {
		
		TerrainNode terrainNode = (TerrainNode) object;
		
		setUniformf("scaleY", terrainNode.getConfig().getScaleY());
		setUniformi("lod", terrainNode.getLod());
		setUniform("index", terrainNode.getIndex());
		setUniform("location", terrainNode.getLocation());
		setUniformf("gap", terrainNode.getGap());
		
		for (int i = 0; i<8; i++){
			setUniformi("lod_morph_area[" + i + "]", terrainNode.getConfig().getLod_morphing_area()[i]);
		}
		
		setUniform("cameraPosition", Camera.getInstance().getPosition());
		setUniform("m_ViewProjection", Camera.getInstance().getViewProjectionMatrix());
		setUniform("localMatrix", object.getLocalTransform().getWorldMatrix());
		setUniform("worldMatrix", object.getWorldTransform().getWorldMatrix());
	}

}

