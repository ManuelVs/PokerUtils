package hja.pokerutils.hand;

import hja.pokerutils.card.Card;

import java.util.ArrayList;

public class HighCard extends Hand {
	
	public HighCard(ArrayList<Card> hand) {
		super(HandType.HIGH_CARD, hand);
	}
	
	@Override
	public int compareKernel(Hand other) {
		Card leftCard = this.hand.get(0);
		Card rightCard = other.hand.get(0);
		
		return leftCard.compareTo(rightCard);
	}
	
	@Override
	public String toString() {
		return "High card: " + hand.get(0).rank.name();
	}
}
