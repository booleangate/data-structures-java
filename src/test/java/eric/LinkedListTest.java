package eric;

import static org.junit.Assert.*;
import eric.LinkedList;

import org.junit.Test;

public class LinkedListTest {
	@Test
	public void deleteWhenEmpty() {
		LinkedList ll = new LinkedList();
		
		try {
			ll.delete(0);
		} catch (IndexOutOfBoundsException e) {
			assertTrue("This should throw IndexOutOfBoundsException, not NullPointerException", true);
		}
	}
}
