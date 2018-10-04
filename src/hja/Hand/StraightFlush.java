package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class StraightFlush extends Hand {
	
	public StraightFlush(ArrayList<Card> hand) {
		super(HandType.STRAIGHT_FLUSH, hand);
	}
	
	@Override
	public int compareKernel(Hand o) {
		Card leftCard = this.hand.get(0);
		Card rightCard = o.hand.get(0);
		return leftCard.compareTo(rightCard);
	}
}
