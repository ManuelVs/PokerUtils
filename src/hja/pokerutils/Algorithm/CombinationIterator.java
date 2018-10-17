package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;
import java.util.Iterator;

public class CombinationIterator implements Iterator<ArrayList<Card>> {
	private ArrayList<Card> cards;
	private int nElements;
	private int[] perm;
	
	public CombinationIterator(ArrayList<Card> cards, int nElements){
		this.cards = cards;
		this.nElements = nElements;
		
		this.perm = new int[cards.size()];
		for(int i = 0; i < nElements; ++i){
			perm[i] = 1;
		}
	}
	
	@Override
	public boolean hasNext() {
		int i = 0;
		while (i < nElements && perm[i] == 1){
			++i;
		}
		
		return i != nElements;
	}
	
	@Override
	public ArrayList<Card> next() {
		nextPermutation();
		
		ArrayList<Card> currentCombination = new ArrayList<>(nElements);
		for(int i = 0; i < cards.size(); ++i){
			if(perm[i] == 1){
				Card currentCard = cards.get(i);
				currentCombination.add(currentCard);
			}
		}
		
		return currentCombination;
	}
	
	private void nextPermutation() {
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
