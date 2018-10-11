package hja.Hand;

import hja.Card.Card;
import hja.Card.Rank;

import java.util.ArrayList;

public class Straight extends Hand {
	
	public Straight(ArrayList<Card> hand) {
		super(HandType.STRAIGHT, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		if (firstCard.rank == Rank.ACE && secondCard.rank == Rank.FIVE) {
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
		return "Straight ended in " + hand.get(0).rank.name();
	}
}