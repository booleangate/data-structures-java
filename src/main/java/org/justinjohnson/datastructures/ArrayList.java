package org.justinjohnson.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
	public static class ArrayListIterator<E> implements ListIterator<E> {
		private int index = 0;
		private final ArrayList<E> array;
		
		public ArrayListIterator(final int index, final ArrayList<E> array) {
			this.index = index;
			this.array = array;
		}

		public boolean hasNext() {
			return index < array.size();
		}

		public E next() {
			return array.get(index++);
		}

		public boolean hasPrevious() {
			return index > 0;
		}

		public E previous() {
			return array.get(index--);
		}

		public int nextIndex() {
			return index + 1;
		}

		public int previousIndex() {
			return index - 1;
		}

		public void remove() {
			array.remove(index);
		}

		public void set(E e) {
			array.set(index, e);
		}

		public void add(E e) {
			array.add(index, e);
		}
	}
	
	private final static float MAXIMUM_UNUSED_CAPACITY_PERCENT = 0.25f;
	private final static int CAPACITY_INCREMENT = 10;

	private int size = 0;
	private E[] data;

	public ArrayList() {
		data = newArray(CAPACITY_INCREMENT);
	}

	public ArrayList(int capacity) {
		data = newArray(capacity);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(Collection<? extends E> c) {
		data = (E[]) c.toArray();
		size = c.size();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	public Iterator<E> iterator() {
		return new ArrayListIterator<E>(0, this);
	}

	public Object[] toArray() {
		return copy(data, newArray(size));
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] dest) {
		if (dest == null) {
			throw new NullPointerException("Destination array cannot be null");
		}

		if (size <= dest.length) {
			return (T[]) copy(data, dest);
		}

		return (T[]) toArray();
	}

	public boolean add(E e) {
		grow(1);
		data[size++] = e;

		return true;
	}

	public boolean remove(Object o) {
		return remove(o, true);
	}

	public boolean containsAll(Collection<?> c) {
		if (c == null) {
			throw new NullPointerException("Collection cannot be null");
		}
		
		for (final Object obj : c) {
			if (!contains(obj)) {
				return false;
			}
		}

		return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		if (c == null) {
			throw new NullPointerException("Collection cannot be null");
		}

		grow(c.size());

		for (final E e : c) {
			add(e);
		}

		return true;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		if (c == null) {
			throw new NullPointerException("Collection cannot be null");
		}
		
		checkBounds(index);
		shift(index, c.size());
		
		for (final E e : c) {
			data[index++] = e;
		}

		return false;
	}

	public boolean removeAll(Collection<?> c) {
		if (c == null) {
			throw new NullPointerException("Collection cannot be null");
		}
		
		boolean modified = false;
		
		for (final Object obj : c) {
			modified = remove(obj, false) || modified;
		}
		
		shrink();

		return modified;
	}

	public boolean retainAll(Collection<?> c) {
		if (c == null) {
			throw new NullPointerException("Collection cannot be null");
		}
		
		boolean modified = false;
		
		for (int i = 0; i < size; ++i) {
			if (!c.contains(data[i])) {
				remove(i, false);
				modified = true;
			}
		}
		
		shrink();

		return modified;
	}

	public void clear() {
		data = newArray(CAPACITY_INCREMENT);
		size = 0;
	}

	public E get(int index) {
		checkBounds(index);

		return data[index];
	}

	public E set(int index, E element) {
		checkBounds(index);

		final E original = data[index];

		data[index] = element;

		return original;
	}

	public void add(int index, E element) {
		checkBounds(index);
		shift(index, 1);
		data[index] = element;
	}

	public E remove(int index) {
		return remove(index, true);
	}

	public int indexOf(Object o) {
		for (int i = 0; i < size; ++i) {
			if (data[i].equals(o)) {
				return i;
			}
		}

		return -1;
	}

	public int lastIndexOf(Object o) {
		for (int i = size; i >= 0; --i) {
			if (data[i].equals(o)) {
				return i;
			}
		}

		return -1;
	}

	public ListIterator<E> listIterator() {
		return new ArrayListIterator<E>(0, this);
	}

	public ListIterator<E> listIterator(int index) {
		checkBounds(index);

		return new ArrayListIterator<E>(index, this);
	}

	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not doing this");
	}

	private Object[] copy(final Object[] source, final Object[] dest) {
		for (int i = 0; i < source.length && i < dest.length; ++i) {
			dest[i] = source[i];
		}

		return dest;
	}

	@SuppressWarnings("unchecked")
	private void grow(int additionalElements) {
		// Only grow if we don't already have the capacity to fit the new capacity (e.g. items).
		if (data.length - size < additionalElements) {
			final int newCapacity = newCapacity(data.length + additionalElements);

			data = (E[]) copy(data, newArray(newCapacity));
		}
	}

	@SuppressWarnings("unchecked")
	private void shrink() {
		// Only shrink if MAXIMUM_UNUSED_CAPACITY_PERCENT of our capacity is unused.
		if (1 - size / data.length >= MAXIMUM_UNUSED_CAPACITY_PERCENT) {
			data = (E[]) copy(data, newArray(size));
		}
	}

	private boolean remove(final Object o, final boolean allowShrink) {
		boolean nullEquality;
		boolean dataEquality;

		for (int i = 0; i < size; ++i) {
			nullEquality = o == null && data[i] == null;
			dataEquality = o != null && data[i].equals(o);

			if (nullEquality || dataEquality) {
				remove(i, allowShrink);

				return true;
			}
		}

		return false;
	}
	
	private E remove(final int index, final boolean allowShrink) {
		checkBounds(index);
		shift(index + 1, -1);
		
		if (allowShrink) {
			shrink();
		}

		return null;
	}

 	private void shift(final int index, final int positions) {
		if (positions > 0) {
			grow(positions);
		}
		
		final int toIndex, fromIndex, incr;
		
		// If things shift to the right, start at the right so we don't overwrite stuff
		if (positions > 0) {
			fromIndex = size - 1;
			toIndex = index;
			incr = -1;
		}
		// If things shift to the left,
		else {
			fromIndex = index;
			toIndex = size - 1;
			incr = 1;
		}
		
		for (int i = fromIndex; i <= toIndex; i += incr) {
			data[i + positions] = data[i];
		}

		if (positions < 0) {
			shrink();
		}
	}

	private void checkBounds(final int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	@SuppressWarnings("unchecked")
	private E[] newArray(final int capacity) {
		return (E[])new Object[capacity];
	}

	private int newCapacity(final int size) {
		// Create a new capacity that fits size and is in increments of CAPACITY_INCREMENT.
		return (int) Math.ceil(size / CAPACITY_INCREMENT) * CAPACITY_INCREMENT;
	}
}
