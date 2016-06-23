package org.justinjohnson.datastructures;

import java.util.List;

public class ArrayListTest extends AbstractListTest {
	@Override
	protected List<Integer> listFactory() {
		return new ArrayList<Integer>();
	}

}
