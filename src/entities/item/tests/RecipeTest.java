package entities.item.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.exceptions.ItemStackException;
import entities.exceptions.NotEnoughSpaceException;
import entities.item.*;

class RecipeTest {

	@Test
	void test() {
		Item iBean = new Item(1, "bean", 20);
		Item iWater = new Item(2, "water", 5);
		Item iCoffee = new Item(3, "coffee", 5);
		
		IItemStack[] testStacks = null;
		Inventory testInventory = null;
		IItemStack[] testInputs = null;
		IItemStack[] testOutputs = null;
		
		try {
			testStacks = new IItemStack[] {new ItemStack(iBean, 20),
				        				   new ItemStack(iWater, 5)};
			testInventory = new Inventory(5, testStacks);
			testInputs = new IItemStack[] {new ItemStack(iBean, 7),
										   new ItemStack(iWater, 1)};
			testOutputs = new IItemStack[] {new ItemStack(iCoffee, 1)};
		} catch (NotEnoughSpaceException e) {
			fail("testInventory");
			e.printStackTrace();
		}		
		
		Recipe rCoffee = new Recipe(testInputs, testOutputs);
		try {
			rCoffee.doRecipie(testInventory);
			rCoffee.doRecipie(testInventory);
			assertEquals(testInventory.getItemNum(iCoffee), 2);
			assertEquals(testInventory.getItemNum(iWater), 3);
			assertEquals(testInventory.getItemNum(iBean), 6);
			assertEquals(testInventory.getMTItems().size(), 2);
		} catch (ItemStackException e) {
			fail("doRecipie twice");
			e.printStackTrace();
		}
		
		try {
			rCoffee.doRecipie(testInventory);
		} catch (ItemStackException e) {
			assertTrue(true);
		}
	}

}
