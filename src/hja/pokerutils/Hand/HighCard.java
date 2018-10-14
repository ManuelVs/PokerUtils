package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class HighCard extends Hand {
	
	public HighCard(ArrayList<Card> hand) {
		super(HandType.HIGH_CARD, hand);
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
	}

	@Override
	public String toString() {
		return "High card: " + hand.get(0).rank.name();
	}
}
