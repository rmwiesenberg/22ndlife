package entities.world;

import java.util.ArrayList;

import org.joml.Vector3i;

import entities.block.IBlock;
import entities.block.MTBlock;
import entities.subject.ISubject;

public class World {
	private IBlock[][][] blocks;
	private ArrayList<ISubject> subjects;
	
	public World(IBlock[][][] blocks, ArrayList<ISubject> subjects){
		this.blocks = blocks;
		this.subjects = subjects;
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
}
