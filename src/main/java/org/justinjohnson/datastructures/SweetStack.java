package org.justinjohnson.datastructures;

/**
 * @author johnsonj
 */
public interface SweetStack {
	/**
	 * Add a new value to the stack.
	 * 
	 * @param value
	 */
	void push(final int value);
	
	/**
	 * Remove the last pushed value from the stack and return it.
	 * 
	 * @return
	 */
	int pop();
	
	/**
	 * Get the size of the stack.
	 * 
	 * @return
	 */
	int size();
	
	/**
	 * Get the largest value within the stack. Does not modify the stack.
	 * 
	 * @return
	 */
	int max();
}
