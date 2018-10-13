package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class TwoPair extends Hand {
	
	public TwoPair(ArrayList<Card> hand) {
		super(HandType.TWO_PAIR, hand);
		sort();
	}
	
	private void sort() {
		Card firstCard = hand.get(0);
		Card secondCard = hand.get(1);
		
		int compare = firstCard.compareTo(secondCard);
		
		if (firstCard.compareTo(secondCard) != 0) {
			swap(hand, 0, 2);
			swap(hand, 4, 2);
		}
		else {
			firstCard = hand.get(2);
			secondCard = hand.get(3);
			if (firstCard.compareTo(secondCard) != 0) {
				swap(hand, 4, 2);
			}
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
		Card leftCard = hand.get(0);
		Card rightCard = o.hand.get(0);
		int compare = leftCard.compareTo(rightCard);
		
		if(compare == 0){
			leftCard = hand.get(2);
			rightCard = o.hand.get(2);
			
			compare = leftCard.compareTo(rightCard);
		}
		
		return compare;
	}
	
	@Override
	public String toString() {
		return "Two pairs of " + hand.get(0).rank.name() + " and " + hand.get(2).rank.name();
	}
}
