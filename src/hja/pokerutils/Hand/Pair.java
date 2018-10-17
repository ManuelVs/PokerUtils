package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Pair extends Hand {
	
	public Pair(ArrayList<Card> hand) {
		super(HandType.PAIR, hand);
		sort();
	}
	
	private void sort() {
		ArrayList<Card> aux = new ArrayList<>(3);
		
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		int compare = firstCard.compareTo(secondCard);
		
		while (compare != 0) {
			Card badCard = this.hand.remove(0);
			aux.add(badCard);
			
			firstCard = hand.get(0);
			secondCard = hand.get(1);
			compare = firstCard.compareTo(secondCard);
		}
		
		this.hand.addAll(2, aux);
	}
	
	private void swap(ArrayList<Card> hand, int i, int j) {
		Card firstCard = hand.get(i);
		Card secondCard = hand.get(j);
		
		hand.set(i, secondCard);
		hand.set(j, firstCard);
	}
	
	@Override
	public int compareKernel(Hand o) {
		Card leftTwoCard = hand.get(0);
		Card rightTwoCard = o.hand.get(0);
		int compare = leftTwoCard.compareTo(rightTwoCard);
		
		return compare;
	}
	
	@Override
	public String toString() {
		return "Pair of " + hand.get(0).rank.name();
	}
}
