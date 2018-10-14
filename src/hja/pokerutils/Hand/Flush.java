package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Flush extends Hand {
	
	public Flush(ArrayList<Card> hand) {
		super(HandType.FLUSH, hand);
	}
	
	@Override
	public int compareKernel(Hand o) {
		int initialPos = 0;
		
		ArrayList<Card> leftHand = this.hand;
		ArrayList<Card> rightHand = o.hand;
		
		int compare;
		do {
			Card leftCard = leftHand.get(initialPos);
			Card rightCard = rightHand.get(initialPos);
			compare = leftCard.compareTo(rightCard);
			initialPos += 1;
		} while (initialPos < 5 && compare == 0);
		
		return compare;
	}
	
	@Override
	public String toString() {
		return "Flush ended in " + hand.get(0).rank.name();
	}
}
