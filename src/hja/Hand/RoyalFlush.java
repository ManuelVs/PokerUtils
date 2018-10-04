package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class RoyalFlush extends Hand {
	
	public RoyalFlush(ArrayList<Card> hand) {
		super(HandType.ROYAL_FLUSH, hand);
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
	}
}
