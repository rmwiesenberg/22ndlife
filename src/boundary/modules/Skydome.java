package boundary.modules;

import boundary.core.buffers.MeshVBO;
import boundary.core.configs.CCW;
import boundary.core.math.Vec3f;
import boundary.core.model.Mesh;
import boundary.core.renderer.RenderInfo;
import boundary.core.renderer.Renderer;
import boundary.core.scene.GameObject;
import boundary.core.utils.Constants;
import boundary.core.utils.objloader.OBJLoader;

public class Skydome extends GameObject {
	
	public Skydome() {
		
		Mesh mesh = new OBJLoader().load("./res/models/dome", "dome.obj", null)[0].getMesh();
		MeshVBO meshBuffer = new MeshVBO();
		meshBuffer.allocate(mesh);
		
		Renderer renderer = new Renderer();
		renderer.setVbo(meshBuffer);
		renderer.setRenderInfo(new RenderInfo(new CCW(), AtmosphereShader.getInstance()));
		addComponent(Constants.RENDERER_COMPONENT, renderer);
		
		getTransform().setScaling(new Vec3f(Constants.ZFAR * 0.5f,
											Constants.ZFAR * 0.5f,
											Constants.ZFAR * 0.5f));
	}

}
