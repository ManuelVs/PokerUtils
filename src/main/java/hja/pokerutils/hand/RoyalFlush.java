package hja.pokerutils.hand;

import hja.pokerutils.card.Card;

import java.util.ArrayList;

public class RoyalFlush extends Hand {
	
	public RoyalFlush(ArrayList<Card> hand) {
		super(HandType.ROYAL_FLUSH, hand);
	}
	
	@Override
	public int compareKernel(Hand other) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "Royal flush";
	}
}
