package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class HighCard extends Hand {
	
	public HighCard(ArrayList<Card> hand) {
		super(HandType.HIGH_CARD, hand);
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
	}
}
