package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class RoyalFlush extends Hand {
	
	public RoyalFlush(ArrayList<Card> hand) {
		super(HandType.ROYAL_FLUSH, hand);
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "Royal flush";
	}
}
