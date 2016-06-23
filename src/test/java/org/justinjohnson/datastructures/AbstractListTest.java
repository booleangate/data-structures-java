package org.justinjohnson.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author johnsonj
 */
@Ignore
public abstract class AbstractListTest {
	protected abstract List<Integer> listFactory();
	
	@Test
	public void sizing() {
		final List<Integer> list = listFactory();
		
		assertEquals("size should be 0 for new list", 0, list.size());
		assertEquals("isEmpty should be true for new list", true, list.isEmpty());

		list.add(1);
		
		assertEquals("size should be 1 after adding first item", 1, list.size());
		assertEquals("isEmpty should be false for non-empty list", false, list.isEmpty());
		
		list.add(2);
		
		assertEquals("size should be 2 after adding second item", 2, list.size());
		
		list.remove(0);
		list.remove(0);
		
		assertEquals("size should be 0 for emptied list", 0, list.size());
		assertEquals("isEmpty should be true for list emptied via remove", true, list.isEmpty());
	}
}
