package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class FourOfAKind extends Hand {
	
	public FourOfAKind(ArrayList<Card> hand) {
		super(HandType.FOUR_OF_A_KIND, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		if (firstCard.compareTo(secondCard) != 0) {
			swap(hand, 0, 4);
		}
	}
	
	private void swap(ArrayList<Card> hand, int i, int j) {
		Card firstCard = hand.get(i);
		Card secondCard = hand.get(j);
		
		hand.set(i, secondCard);
		hand.set(j, firstCard);
	}
	
	@Override
	public int compareKernel(Hand o) {
		Card leftCard = this.hand.get(0);
		Card rightCard = o.hand.get(0);
		return leftCard.compareTo(rightCard);
	}
	
	@Override
	public String toString() {
		return "Four of " + hand.get(0).rank.name();
	}
}