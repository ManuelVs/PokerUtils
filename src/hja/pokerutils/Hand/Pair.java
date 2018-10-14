package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Pair extends Hand {
	
	public Pair(ArrayList<Card> hand) {
		super(HandType.PAIR, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		int compare = firstCard.compareTo(secondCard);
		int index = 1;
		
		while (index < hand.size() - 1 && compare != 0) {
			firstCard = hand.get(index);
			secondCard = hand.get(index + 1);
			
			compare = firstCard.compareTo(secondCard);
			++index;
		}
		
		swap(hand, index - 1, 0);
		swap(hand, index, 1);
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
