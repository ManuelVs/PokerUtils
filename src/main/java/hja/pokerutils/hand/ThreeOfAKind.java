package hja.pokerutils.hand;

import hja.pokerutils.card.Card;

import java.util.ArrayList;
import java.util.Collections;

public class ThreeOfAKind extends Hand {
	
	public ThreeOfAKind(ArrayList<Card> hand) {
		super(HandType.THREE_OF_A_KIND, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		Card thirdCard = hand.get(3);
		
		if (firstCard.compareTo(secondCard) != 0) {
			if (secondCard.compareTo(thirdCard) != 0) {
				Collections.swap(hand, 0, 4);
				Collections.swap(hand, 1, 3);
			}
			else {
				Collections.swap(hand, 0, 3);
			}
		}
	}
	
	@Override
	public int compareKernel(Hand other) {
		Card leftThreeCard = hand.get(0);
		Card rightThreeCard = other.hand.get(0);
		
		return leftThreeCard.compareTo(rightThreeCard);
	}
	
	@Override
	public String toString() {
		return "Three " + hand.get(0).rank.name();
	}
}
