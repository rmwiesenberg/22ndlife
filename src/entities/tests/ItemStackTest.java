package entities.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.*;
import entities.exceptions.*;
import entities.item.Item;
import entities.item.ItemStack;

class ItemStackTest {
	@Test
	void testStackBasic() {
		Item cup = new Item(5, "cup", 20);
		ItemStack testStack = null;
		
		// constructor
		
		try {
			testStack = new ItemStack(cup, 21);
		} catch (NotEnoughSpaceException e) {
			assertTrue(true);
		}
		
		try {
			testStack = new ItemStack(cup, 20);
			assertTrue(true);
		} catch (NotEnoughSpaceException e) {
			fail("failed on cup 20");
		}
		assertEquals(testStack.getID(), 5);
		assertEquals(testStack.getName(), "cup");
		assertEquals(testStack.getCurSize(), 20);
		assertEquals(testStack.getMaxSize(), 20);
	}
	
	@Test
	void testStackAddRem() {
		Item cup = new Item(5, "cup", 20);
		ItemStack testStack = null;
		ItemStack cup5 = null;
		ItemStack cup10 = null;
		try {
			testStack = new ItemStack(cup, 15);
			cup5 = new ItemStack(cup, 5);
			cup10 = new ItemStack(cup, 10);
		} catch (NotEnoughSpaceException e) {
			e.printStackTrace();
		}
		
		// adding
		try {
			testStack.addStack(cup10);
		} catch (ItemStackException e) {
			assertTrue(true);
		}
		try {
			testStack.addStack(cup5);
			assertEquals(testStack.getCurSize(), 20);
		} catch (ItemStackException e) {
			fail("failed on adding stack");
		}
		
		// removing
		try {
			testStack.removeStack(cup5);
			testStack.removeStack(cup10);
			assertEquals(testStack.getCurSize(), 5);
		} catch (ItemStackException e) {
			fail("failed on removing stack");
		}
		try {
			testStack.removeStack(cup10);
		} catch (ItemStackException e) {
			assertTrue(true);
		}
		
		// set to 0
		try {
			testStack.removeStack(cup5);
			assertEquals(testStack.getCurSize(), 0);
		} catch (ItemStackException e) {
			fail("failed removing to zero");
		}
	}
	
	@Test
	void testMTItemStack() {
		
	}

}
