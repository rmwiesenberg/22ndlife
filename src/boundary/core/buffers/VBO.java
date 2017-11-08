package boundary.core.buffers;

import boundary.core.model.Mesh;

public interface VBO {
	
	
	public void allocate(Mesh mesh);
	public void draw();
	public void delete();
}
