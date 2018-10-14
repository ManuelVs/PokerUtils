package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;

import java.util.ArrayList;

public class StraightFlush extends Hand {
	
	public StraightFlush(ArrayList<Card> hand) {
		super(HandType.STRAIGHT_FLUSH, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		
		if (firstCard.rank == Rank.ACE) {
			// Straight flush of 5,4,3,2,A
			hand.remove(0);
			hand.add(firstCard);
		}
	}
	
	@Override
	public int compareKernel(Hand o) {
		Card leftCard = this.hand.get(0);
		Card rightCard = o.hand.get(0);
		return leftCard.compareTo(rightCard);
	}

	@Override
	public String toString() {
		return "Straight flush ended in " + hand.get(0).rank.name();
	}
}
