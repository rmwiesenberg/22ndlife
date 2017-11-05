package entities.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.*;

class InventoryTest {

	@Test
	void testAddRemove() {
		Item testItem = new Item(5, "cups", 20);
		Inventory testInventory = new Inventory(5);
	}

}
