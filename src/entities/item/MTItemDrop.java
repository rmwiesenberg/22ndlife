package entities.item;

public class MTItemDrop implements IItemDrop {

	@Override
	public IItemStack[] generate() {
		return new MTItemStack[0];
	}

	@Override
	public IItemStack[] generate(float modifier) {
		return new MTItemStack[0];
	}

	@Override
	public IItem[] getItems() {
		return new MTItem[0];
	}

	@Override
	public int[] getMin() {
		return new int[0];
	}

	@Override
	public int[] getMax() {
		return new int[0];
	}

	@Override
	public float[] getChance() {
		return new float[0];
	}

}
