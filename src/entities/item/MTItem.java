package entities.item;

public class MTItem implements IItem {

	public MTItem() {}
	
	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public String getName() {
		return "empty";
	}
	
	@Override
	public int getMaxSize() {
		return 0;
	}
}
