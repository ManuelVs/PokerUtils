package hja.Hand;

import hja.Card.Card;

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
				swap(hand, 0, 4);
				swap(hand, 1, 3);
			}
			else {
				swap(hand, 0, 3);
			}
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
		Card rightThreeCard = o.hand.get(0);
		int compare = leftThreeCard.compareTo(rightThreeCard);
		
		return compare;
	}
	
	@Override
	public String toString() {
		return "Three " + hand.get(0).rank.name();
	}
}
