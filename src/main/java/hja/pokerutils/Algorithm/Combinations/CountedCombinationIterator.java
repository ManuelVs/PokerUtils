package hja.pokerutils.Algorithm.Combinations;

import java.util.ArrayList;

public class CountedCombinationIterator<E> extends CombinationIterator<E> {
	protected int size;
	
	public CountedCombinationIterator(ArrayList<E> elements, int nElements) {
		super(elements, nElements);
	}
	
	public CountedCombinationIterator(CountedCombinationIterator<E> other) {
		super(other.elements, other.nElements);
		this.perm = other.perm.clone();
		this.begin = other.begin;
		this.size = other.size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public boolean hasNext() {
		return size > 0;
	}
	
	@Override
	public ArrayList<E> next() {
		this.size--;
		return super.next();
	}
}
