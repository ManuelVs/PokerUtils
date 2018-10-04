package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class FullHouse extends Hand {
	
	public FullHouse(ArrayList<Card> hand) {
		super(HandType.FULL_HOUSE, hand);
		sort();
	}
	
	private void sort() {
		if (hand.get(1).rank != hand.get(2).rank) {
			swap(hand, 0, 3);
			swap(hand, 1, 4);
		}
	}
	
	private void swap(ArrayList<Card> hand, int i, int j) {
		Card firstCard = hand.get(i);
		Card secondCard = hand.get(j);
		
		hand.set(i, secondCard);
		hand.set(j, firstCard);
	}
	
	@Override
	public int compareKernel(Hand o) {
		Card leftThreeCard = hand.get(0);
		Card leftTwoCard = hand.get(3);
		Card rightThreeCard = o.hand.get(0);
		Card rightTwoCard = o.hand.get(3);
		
		int compare = leftThreeCard.compareTo(rightThreeCard);
		if (compare == 0) {
			compare = leftTwoCard.compareTo(rightTwoCard);
		}
		
		return compare;
	}
}
