package hja.pokerutils.Algorithm;

import java.util.ArrayList;
import java.util.Iterator;

public class CombinationCalculator<E> implements Iterable<ArrayList<E>> {
	private final ArrayList<E> elements;
	private final int nElements;
	
	public CombinationCalculator(ArrayList<E> elements, int nElements) {
		this.elements = elements;
		this.nElements = nElements;
	}
	
	@Override
	public Iterator<ArrayList<E>> iterator() {
		return new CombinationIterator<>(elements, nElements);
	}
}
