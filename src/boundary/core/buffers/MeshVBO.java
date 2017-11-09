package boundary.core.buffers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import boundary.core.model.Mesh;
import boundary.core.utils.BufferUtil;

public class MeshVBO implements VBO{
	
	private int vbID;
	private int ibID;
	private int vaoID;
	private int size;
	
	public MeshVBO() {
		
		vbID = GL15.glGenBuffers();
		ibID = GL15.glGenBuffers();
		vaoID = GL30.glGenVertexArrays();
	
	}

	public void allocate(Mesh mesh) {
		
		size = mesh.getIndices().length;
		
		GL30.glBindVertexArray(vaoID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtil.createFlippedBufferAOS(mesh.getVertices()), GL15.GL_STATIC_DRAW);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, BufferUtil.createFlippedBuffer(mesh.getIndices()), GL15.GL_STATIC_DRAW);
		
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, Float.BYTES * 8, 0);
		GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, Float.BYTES * 8, Float.BYTES * 3);
		GL20.glVertexAttribPointer(2, 3, GL11.GL_FLOAT, false, Float.BYTES * 8, Float.BYTES * 6);
		
		GL30.glBindVertexArray(0);
		
	}

	@Override
	public void draw() {
		
		GL30.glBindVertexArray(vaoID);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, size, GL11.GL_UNSIGNED_INT, 0);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		
		GL30.glBindVertexArray(0);

		
	}

	@Override
	public void delete() {
		
		GL30.glBindVertexArray(vaoID);
		GL15.glDeleteBuffers(vbID);
		GL15.glDeleteBuffers(ibID);
		GL30.glDeleteVertexArrays(vaoID);
		GL30.glBindVertexArray(0);
		
	}

}
