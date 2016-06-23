package org.justinjohnson.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

import org.junit.Test;
import org.justinjohnson.datastructures.SweetStack;
import org.justinjohnson.datastructures.SweetStackImpl;

/**
 * @author johnsonj
 */
public class SweetStackTest {
	@Test
	public void size() {
		final SweetStack stack = new SweetStackImpl();
		
		assertEquals("Stack size should be 0 for a new stack", stack.size(), 0);

		stack.push(10);
		
		assertEquals("Stack size should be 1 after pushing the first entry", stack.size(), 1);
		
		stack.push(20);
		
		assertEquals("Stack size should be 2 after pushing the second entry", stack.size(), 2);
		
		stack.pop();
		stack.pop();
		
		assertEquals("Stack size should be 0 for a stack that has been completely popped", stack.size(), 0);
	}
	
	@Test
	public void values() {
		final SweetStack stack = new SweetStackImpl();
		
		stack.push(10);
		stack.push(20);
		stack.push(30);
		
		assertEquals("Pop should return 30", stack.pop(), 30);
		assertEquals("Pop should return 20", stack.pop(), 20);
		assertEquals("Pop should return 10", stack.pop(), 10);
	}
	
	@Test(expected=EmptyStackException.class)
	public void emptyPop() {
		final SweetStack stack = new SweetStackImpl();
		
		stack.pop();
	}
	
	@Test
	public void max() {
		final SweetStack stack = new SweetStackImpl();
		
		// Test negatives
		stack.push(-10);
		
		assertEquals("Stack max should be -1", stack.max(), -10);
		
		// Test new max
		stack.push(0);
		
		assertEquals("Stack max should be 0", stack.max(), 0);
		
		// Test previous entry as the max
		stack.push(7);
		stack.push(3);
		
		assertEquals("Stack max should be 7", stack.max(), 7);
		
		// Test new max and pop to make the last entry the max
		stack.push(8);
		stack.push(9);
		stack.pop();
		
		assertEquals("Stack max should be 8", stack.max(), 8);
	}
}
