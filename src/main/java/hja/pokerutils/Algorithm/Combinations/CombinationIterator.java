package hja.pokerutils.Algorithm.Combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CombinationIterator<E> implements Iterator<ArrayList<E>> {
	protected ArrayList<E> elements;
	protected int nElements;
	protected int[] perm;
	protected boolean begin;
	
	public CombinationIterator(ArrayList<E> elements, int nElements) {
		this.elements = elements;
		this.nElements = nElements;
		
		this.perm = new int[elements.size()];
		for (int i = 0; i < nElements; ++i) {
			perm[i] = 1;
		}
		
		this.begin = true;
		reset();
	}
	
	public void reset() {
		for (int i = 0; i < nElements; ++i) {
			perm[i] = 1;
		}
		
		this.begin = true;
	}
	
	@Override
	public boolean hasNext() {
		int i = 0;
		while (i < nElements && perm[i] == 1) {
			++i;
		}
		
		return i != nElements || this.begin;
	}
	
	@Override
	public ArrayList<E> next() {
		this.begin = false;
		nextPermutation();
		
		//ArrayList<E> currentCombination = new ArrayList<>(nElements);
		int j = 0;
		E[] currentCombination = (E[]) new Object[nElements];
		for (int i = 0; i < elements.size(); ++i) {
			if (perm[i] == 1) {
				E currentElement = elements.get(i);
				currentCombination[j] = currentElement;
				++j;
			}
		}
		
		return new ArrayList<>(Arrays.asList(currentCombination));
	}
	
	protected void nextPermutation() {
		int i = perm.length - 1;
		while (i > 0 && perm[i] <= perm[i - 1]) {
			i--;
		}
		
		if (i > 0) {
			int j = perm.length - 1;
			while (j >= 0 && perm[j] <= perm[i - 1]) {
				j--;
			}
			swap(i - 1, j);
		}
		
		reverse(i);
	}
	
	private void reverse(int start) {
		int i = start, j = perm.length - 1;
		while (i < j) {
			swap(i, j);
			++i;
			--j;
		}
	}
	
	private void swap(int i, int j) {
		int temp = perm[i];
		perm[i] = perm[j];
		perm[j] = temp;
	}
}
