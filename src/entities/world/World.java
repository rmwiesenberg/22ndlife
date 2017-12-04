package entities.world;

import java.util.ArrayList;

import org.joml.Vector3i;

import entities.block.IBlock;
import entities.block.MTBlock;
import entities.subject.ISubject;

public class World {
	private IBlock[][][] blocks;
	private ArrayList<ISubject> subjects;
	private Camera camera;
	
	public World(IBlock[][][] blocks, ArrayList<ISubject> subjects, Camera camera){
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
	
	void addBlock(IBlock block, Vector3i pos) {
		blocks[pos.x][pos.y][pos.z] = block;
	}
	
	void remBlock(Vector3i pos) {
		addBlock(new MTBlock(), pos);
	}
	
	void addSubject(ISubject subj) {
		subjects.add(subj);
	}
	
	void remSubject(ISubject subj) {
		subjects.remove(subj);
	}

	// Getters and Setters
	public IBlock[][][] getBlocks() {
		return blocks;
	}

	public ArrayList<ISubject> getSubjects() {
		return subjects;
	}
	
	public Camera getCamera() {
		return camera;
	}
}
