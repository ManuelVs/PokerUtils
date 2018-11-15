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
	
	@Override
	public int compareKernel(Hand o) {
		Card leftTwoCard = hand.get(0);
		Card rightTwoCard = o.hand.get(0);

		return leftTwoCard.compareTo(rightTwoCard);
	}
	
	@Override
	public String toString() {
		return "Pair of " + hand.get(0).rank.name();
	}
}
