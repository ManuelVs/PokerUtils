package hja.Hand;

import hja.Card.Card;

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
		
		Card leftCard = leftHand.get(initialPos);
		Card rightCard = rightHand.get(initialPos);
		
		int compare = leftCard.compareTo(rightCard);
		while (initialPos < 5 && compare == 0) {
			initialPos += 1;
			leftCard = leftHand.get(initialPos);
			rightCard = rightHand.get(initialPos);
			compare = leftCard.compareTo(rightCard);
		}
		
		return compare;
	}
}
