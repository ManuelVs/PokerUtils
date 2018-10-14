package hja.pokerutils.Hand;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public abstract class Hand implements Comparable<Hand> {
	protected final HandType type;
	protected final ArrayList<Card> hand;
	
	Hand(HandType type, ArrayList<Card> hand) {
		this.type = type;
		this.hand = new ArrayList<Card>(hand);
	}
	
	@Override
	public int compareTo(Hand o) {
		int compare = this.type.compareTo(o.type);
		
		if (compare == 0) {
			compare = this.compareKernel(o);
			
			if (compare == 0) {
				compare = this.compareKickers(o);
			}
		}
		
		return compare;
	}
	
	protected final int compareKickers(Hand o) {
		int initialPos = this.type.firstKicker;
		if (initialPos == 5) return 0;
		
		ArrayList<Card> leftHand = this.hand;
		ArrayList<Card> rightHand = o.hand;
		
		int compare;
		do {
			Card leftCard = leftHand.get(initialPos);
			Card rightCard = rightHand.get(initialPos);
			compare = leftCard.compareTo(rightCard);
			initialPos += 1;
		} while (compare == 0 && initialPos < 5);
		
		return compare;
	}
	
	protected abstract int compareKernel(Hand o);
	
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	public abstract String toString();
}
