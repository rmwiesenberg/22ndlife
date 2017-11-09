package entities.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entities.exceptions.ItemStackException;
import entities.exceptions.NotEnoughSpaceException;
import entities.item.IItemStack;
import entities.item.Inventory;
import entities.item.Item;
import entities.item.ItemStack;
import entities.item.MTItemStack;

class InventoryTest {

	@Test
	void testInventoryBasic() {
		Item cup = new Item(5, "cups", 20);
		IItemStack cup5 = null;
		IItemStack mtStack = null;
		try {
			cup5 = new ItemStack(cup, 5);
			mtStack = new MTItemStack();
		} catch (NotEnoughSpaceException e) {
			e.printStackTrace();
		}
		
		Inventory testInventory = new Inventory(5);
		
		ArrayList<Integer> mtItems = testInventory.getMTItems();
		assertEquals(mtItems.size(), 5);
		assertTrue(mtItems.contains(0));
		assertTrue(mtItems.contains(1));
		assertTrue(mtItems.contains(2));
		assertTrue(mtItems.contains(3));
		assertTrue(mtItems.contains(4));
		
		IItemStack[] items = testInventory.getItems();
		items[1] = cup5;
		
		try {
			testInventory = new Inventory(8, items);
			mtItems = testInventory.getMTItems();
			assertEquals(mtItems.size(), 7);
			assertTrue(mtItems.contains(0));
			assertTrue(mtItems.contains(2));
			assertTrue(mtItems.contains(3));
			assertTrue(mtItems.contains(4));
			assertTrue(mtItems.contains(5));
			assertTrue(mtItems.contains(6));
			assertTrue(mtItems.contains(7));
		} catch (NotEnoughSpaceException e) {
			e.printStackTrace();
			fail("get MT");
		}
		
		try {
			testInventory = new Inventory(3, items);
		} catch (NotEnoughSpaceException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testInventoryAddRem() {
		Item cup = new Item(5, "cups", 20);
		Item bean = new Item(3, "bean", 5);
		IItemStack cup5 = null;
		IItemStack bean5 = null;
		IItemStack mtStack = null;
		
		try {
			cup5 = new ItemStack(cup, 5);
			mtStack = new MTItemStack();
		} catch (NotEnoughSpaceException e) {
			e.printStackTrace();
		}
		
		Inventory testInventory = new Inventory(5);
		ArrayList<Integer> mtItems = null;
		
		try {
			testInventory.addItem(cup5);
			mtItems = testInventory.getMTItems();
			assertEquals(mtItems.size(), 4);
			assertTrue(mtItems.contains(1));
			assertTrue(mtItems.contains(2));
			assertTrue(mtItems.contains(3));
			assertTrue(mtItems.contains(4));
			assertEquals(testInventory.getItem(0).getCurSize(), 5);
		} catch (ItemStackException e) {
			e.printStackTrace();
			fail("add to inventory");
		}
		
		try {
			testInventory.addItem(mtStack);
			mtItems = testInventory.getMTItems();
			assertEquals(mtItems.size(), 4);
			assertTrue(mtItems.contains(1));
			assertTrue(mtItems.contains(2));
			assertTrue(mtItems.contains(3));
			assertTrue(mtItems.contains(4));
		} catch (ItemStackException e) {
			e.printStackTrace();
			fail("add MT");
		}
		
		try {
			cup5 = new ItemStack(cup, 5);
			bean5 = new ItemStack(bean, 5);
			testInventory.addItem(bean5);
			testInventory.addItem(cup5);
			mtItems = testInventory.getMTItems();
			assertEquals(mtItems.size(), 3);
			assertTrue(mtItems.contains(2));
			assertTrue(mtItems.contains(3));
			assertTrue(mtItems.contains(4));
			assertEquals(testInventory.getItem(0).getCurSize(), 10);
			assertEquals(testInventory.getItem(0).getID(), 5);
			assertEquals(testInventory.getItem(1).getCurSize(), 5);
			assertEquals(testInventory.getItem(1).getID(), 3);
		} catch (ItemStackException e) {
			e.printStackTrace();
			fail("adding cups and beans");
		}
		
		try {
			cup5 = new ItemStack(cup, 5);
			bean5 = new ItemStack(bean, 5);
			testInventory.removeItem(bean5);
			testInventory.removeItem(cup5);
			mtItems = testInventory.getMTItems();
			assertEquals(mtItems.size(), 4);
			assertTrue(mtItems.contains(1));
			assertTrue(mtItems.contains(2));
			assertTrue(mtItems.contains(3));
			assertTrue(mtItems.contains(4));
			assertEquals(testInventory.getItem(0).getCurSize(), 5);
			assertEquals(testInventory.getItem(0).getID(), 5);
		} catch (ItemStackException e) {
			e.printStackTrace();
			fail("removing from inventory");
		}
	}

}
