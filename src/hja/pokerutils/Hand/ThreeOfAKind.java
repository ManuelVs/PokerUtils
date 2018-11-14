package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Utils;

import java.util.ArrayList;

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
				Utils.swap(hand, 0, 4);
				Utils.swap(hand, 1, 3);
			}
			else {
				Utils.swap(hand, 0, 3);
			}
		}
	}
	
	@Override
	public int compareKernel(Hand o) {
		Card leftThreeCard = hand.get(0);
		Card rightThreeCard = o.hand.get(0);
		
		return leftThreeCard.compareTo(rightThreeCard);
	}
	
	@Override
	public String toString() {
		return "Three " + hand.get(0).rank.name();
	}
}
