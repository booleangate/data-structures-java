package org.justinjohnson.datastructures;

import java.util.EmptyStackException;

/**
 * @author johnsonj
 */
public class SweetStackImpl implements SweetStack {
	/**
	 * Nodes create the structure of the stack (the ordering of pushed entries) and keep track of the maximum value in the substack that they are on top of.
	 */
	private class Node {
		public final Node previous;
		public final Node max;
		public final int value;

		/**
		 * @param previous The node that was previously on the top.
		 * @param value The value being added to the stack.
		 */
		public Node(final Node previous, final int value) {
			this.previous = previous;
			this.value = value;

			// If there is no previous max or the previous max is less than the new value, then this node is the new max.
			if (previous == null || previous.getMax() < value) {
				max = this;
			}
			// Otherwise, this substack's max is the same as the last substack's max.
			else {
				max = previous.max;
			}
		}

		public int getMax() {
			return max.value;
		}
	}

	private Node top = null;
	private int size = 0;

	public void push(final int value) {
		top = new Node(top, value);

		++size;
	}

	public int pop() {
		ensureNonEmpty();

		final Node poppedNode = top;

		top = top.previous;
		--size;

		return poppedNode.value;
	}

	public int size() {
		return size;
	}

	public int max() {
		ensureNonEmpty();

		return top.getMax();
	}

	/**
	 * If the stack is empty, raise a EmptyStackException exception.
	 */
	private void ensureNonEmpty() {
		if (size == 0) {
			throw new EmptyStackException();
		}
	}
}