package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Pair extends Hand {
	
	public Pair(ArrayList<Card> hand) {
		super(HandType.PAIR, hand);
		sort();
	}
	
	private void sort() {
		/*
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
		*/
		
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		int compare = firstCard.compareTo(secondCard);
		int i = 1;
		while (i + 1 < hand.size() && compare != 0) {
			firstCard = hand.get(i);
			secondCard = hand.get(i + 1);
			
			compare = firstCard.compareTo(secondCard);
			++i;
		}
		
		for (int j = i; j - 2 >= 0; --j) {
			Card card = hand.get(j - 2);
			hand.set(j, card);
		}
		hand.set(0, firstCard);
		hand.set(1, secondCard);
	}
	
	@Override
	public int compareKernel(Hand other) {
		Card leftTwoCard = hand.get(0);
		Card rightTwoCard = other.hand.get(0);
		
		return leftTwoCard.compareTo(rightTwoCard);
	}
	
	@Override
	public String toString() {
		return "Pair of " + hand.get(0).rank.name();
	}
}
