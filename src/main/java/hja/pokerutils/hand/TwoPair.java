package hja.pokerutils.hand;

import hja.pokerutils.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class TwoPair extends Hand {
	
	public TwoPair(ArrayList<Card> hand) {
		super(HandType.TWO_PAIR, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		if (firstCard.compareTo(secondCard) != 0) {
			Collections.swap(hand, 0, 2);
			Collections.swap(hand, 4, 2);
		}
		else {
			firstCard = hand.get(2);
			secondCard = hand.get(3);
			if (firstCard.compareTo(secondCard) != 0) {
				Collections.swap(hand, 4, 2);
			}
		}
	}
	
	@Override
	public int compareKernel(Hand other) {
		Card leftCard = hand.get(0);
		Card rightCard = other.hand.get(0);
		int compare = leftCard.compareTo(rightCard);
		
		if (compare == 0) {
			leftCard = hand.get(2);
			rightCard = other.hand.get(2);
			
			compare = leftCard.compareTo(rightCard);
		}
		
		return compare;
	}
	
	@Override
	public String toString() {
		return "Two pairs of " + hand.get(0).rank.name() + " and " + hand.get(2).rank.name();
	}
}
