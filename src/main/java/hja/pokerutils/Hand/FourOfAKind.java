package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class FourOfAKind extends Hand {
	
	public FourOfAKind(ArrayList<Card> hand) {
		super(HandType.FOUR_OF_A_KIND, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		if (firstCard.compareTo(secondCard) != 0) {
			Collections.swap(hand, 0, 4);
		}
	}
	
	@Override
	public int compareKernel(Hand other) {
		Card leftCard = this.hand.get(0);
		Card rightCard = other.hand.get(0);
		return leftCard.compareTo(rightCard);
	}
	
	@Override
	public String toString() {
		return "Four of " + hand.get(0).rank.name();
	}
}
