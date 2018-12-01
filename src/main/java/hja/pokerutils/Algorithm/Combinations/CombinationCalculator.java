package hja.pokerutils.Algorithm.Combinations;

import java.util.ArrayList;
import java.util.Iterator;

public class CombinationCalculator<E> implements Iterable<ArrayList<E>> {
	private final ArrayList<E> elements;
	private final int nElements;
	
	public CombinationCalculator(ArrayList<E> elements, int nElements) {
		this.elements = elements;
		this.nElements = nElements;
	}
	
	private static int numCombinations(int n, int m) {
		int total = 1;
		for (int i = m; i > (m - n); --i) {
			total *= i;
		}
		// total = m! / (m - n)! = m*(m-1)*...*(m-n+1)
		
		int nFact = 1;
		for (int i = n; i > 1; --i) {
			nFact *= i;
		}
		// total = m! / ( n! * (m - n)! )
		
		return total / nFact;
	}
	
	@Override
	public Iterator<ArrayList<E>> iterator() {
		return new CombinationIterator<>(elements, nElements);
	}
	
	public Iterator<CountedCombinationIterator<E>> iterators(int numSequences) {
		return new CountedCombinationIteratorIterator<>(nElements, elements, numSequences);
	}
	
	private static class CountedCombinationIteratorIterator<E> implements Iterator<CountedCombinationIterator<E>> {
		private int numSequences;
		private int sizeOfSequence;
		private int remaining;
		
		private int numProvided;
		
		private CountedCombinationIterator<E> originalIterator;
		
		public CountedCombinationIteratorIterator(int nElements, ArrayList<E> elements, int numSequences) {
			int numCombinations = numCombinations(nElements, elements.size());
			this.numSequences = numSequences;
			this.sizeOfSequence = numCombinations / numSequences;
			this.remaining = numCombinations % numSequences;
			this.numProvided = 0;
			
			this.originalIterator = new CountedCombinationIterator<>(elements, nElements);
		}
		
		@Override
		public boolean hasNext() {
			return numProvided != numSequences;
		}
		
		@Override
		public CountedCombinationIterator<E> next() {
			CountedCombinationIterator<E> it;
			if (numProvided == 0) {
				it = new CountedCombinationIterator<>(originalIterator);
				it.setSize(sizeOfSequence + remaining);
			}
			else if (numProvided == 1) {
				for (int i = 0; i < sizeOfSequence + remaining; ++i) {
					originalIterator.nextPermutation();
				}
				it = new CountedCombinationIterator<>(originalIterator);
				it.setSize(sizeOfSequence);
			}
			else {
				for (int j = 0; j < sizeOfSequence; ++j) {
					originalIterator.nextPermutation();
				}
				it = new CountedCombinationIterator<>(originalIterator);
				it.setSize(sizeOfSequence);
			}
			
			++numProvided;
			return it;
		}
	}
}
