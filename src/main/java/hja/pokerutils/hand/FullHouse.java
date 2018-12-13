package hja.pokerutils.hand;

import hja.pokerutils.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class FullHouse extends Hand {
	
	public FullHouse(ArrayList<Card> hand) {
		super(HandType.FULL_HOUSE, hand);
		sort();
	}
	
	private void sort() {
		if (hand.get(1).rank != hand.get(2).rank) {
			Collections.swap(hand, 0, 3);
			Collections.swap(hand, 1, 4);
		}
	}
	
	@Override
	public int compareKernel(Hand other) {
		Card leftThreeCard = hand.get(0);
		Card leftTwoCard = hand.get(3);
		Card rightThreeCard = other.hand.get(0);
		Card rightTwoCard = other.hand.get(3);
		
		int compare = leftThreeCard.compareTo(rightThreeCard);
		if (compare == 0) {
			compare = leftTwoCard.compareTo(rightTwoCard);
		}
		
		return compare;
	}
	
	@Override
	public String toString() {
		return "Full of " + hand.get(0).rank.name() + " and " + hand.get(3).rank.name();
	}
}
