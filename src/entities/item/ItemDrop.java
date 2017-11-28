package entities.item;

import java.util.Random;

import entities.exceptions.NotEnoughSpaceException;

public class ItemDrop {
	private Item[] items;
	private int[] min;
	private int[] max;
	private float[] chance;
	
	/**
	 * 
	 * @param items array of items
	 * @param min array of minimum amount to drop
	 * @param max array of maximum amount to drop
	 * @param chance array of chance*100% that ONE of an item is dropped
	 */
	public ItemDrop(Item[] items, int[] min, int[] max, float[] chance) {
		this.items = items;
		this.min = min;
		this.max = max;
		this.chance = chance;
	}
	
	/**
	 * 
	 * @return ItemStacks of items from generation
	 */
	public ItemStack[] generate() {
		Random rand = new Random();
		ItemStack[] stacks = new ItemStack[items.length];
		for(int i = 0; i < items.length; i++) {
			int num = min[i];
			for(int p = 0; p < (max[i] - min[i]); p++) {
				if(rand.nextFloat() +  chance[i] > 1) {
					num++;
				}
			}
			try {
				stacks[i] = new ItemStack(items[i], num);
			} catch (NotEnoughSpaceException e) {
				e.printStackTrace();
			}
		}
		return stacks;
	}
	
	/**
	 * 
	 * @param modifier multiplier - positive if multiply, negative if minimum, zero if 1 for each
	 * @return
	 */
	public ItemStack[] generate(float modifier) {
		Random rand = new Random();
		ItemStack[] stacks = new ItemStack[items.length];
		if(modifier > 0) {
			for(int i = 0; i < items.length; i++) {
				int num = min[i];
				for(int p = 0; p < (max[i] - min[i]); p++) {
					if(rand.nextFloat() +  chance[i]*modifier > 1) {
						num++;
					}
				}
				try {
					stacks[i] = new ItemStack(items[i], num);
				} catch (NotEnoughSpaceException e) {
					e.printStackTrace();
				}
			}
		} else if(modifier < 0) {
			for(int i = 0; i < items.length; i++) {
				int num = min[i];
				try {
					stacks[i] = new ItemStack(items[i], num);
				} catch (NotEnoughSpaceException e) {
					e.printStackTrace();
				}
			}
		} else {
			for(int i = 0; i < items.length; i++) {
				try {
					stacks[i] = new ItemStack(items[i], 1);
				} catch (NotEnoughSpaceException e) {
					e.printStackTrace();
				}
			}
		}
		return stacks;
	}
	
	// Getters and Setters
	public Item[] getItems() {
		return items;
	}

	public int[] getMin() {
		return min;
	}

	public int[] getMax() {
		return max;
	}

	public float[] getChance() {
		return chance;
	}
}
