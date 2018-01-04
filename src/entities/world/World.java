package entities.world;

import java.util.ArrayList;
import java.util.HashMap;

import org.joml.Vector3i;

import entities.block.IBlock;
import entities.block.MTBlock;
import entities.subject.ISubject;

public class World {
	private HashMap<Vector3i, IBlock> blocks;
	private ArrayList<ISubject> subjects;
	private Camera camera;
	
	public World(HashMap<Vector3i, IBlock> blocks,
				 ArrayList<ISubject> subjects, Camera camera){
		this.blocks = blocks;
		this.subjects = subjects;
		this.camera = camera;
	}
	
	public World(String seed) {
		generateWorld(seed);
	}
	
	private void generateWorld(String seed) {
		// TODO
		return;
	}

    public void addBlock(IBlock block, Vector3i pos) {
		blocks.put(pos, block);
	}

    public void remBlock(Vector3i pos) {
		addBlock(new MTBlock(), pos);
	}

    public void addSubject(ISubject subj) {
		subjects.add(subj);
	}

    public void remSubject(ISubject subj) {
		subjects.remove(subj);
	}

	public boolean hasBlockAt(Vector3i pos) {
	    return (blocks.containsKey(pos));
    }

    public boolean hasBlockAt(int x, int y, int z) {
        return hasBlockAt(new Vector3i(x, y, z));
    }

	// Getters and Setters
	public HashMap<Vector3i, IBlock> getBlocks() {
		return blocks;
	}

    /**
     * gets block at specified position
     * @param pos Vector3i
     * @return block at position, MTBlock if not hasBlockAt
     */

	public IBlock getBlock(Vector3i pos) {
	    if(!hasBlockAt(pos)) {
	        return new MTBlock();
        } else {
	        return blocks.get(pos);
        }
    }

	public ArrayList<ISubject> getSubjects() {
		return subjects;
	}
	
	public Camera getCamera() {
		return camera;
	}
}
