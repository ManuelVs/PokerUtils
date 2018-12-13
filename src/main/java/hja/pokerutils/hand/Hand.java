package hja.pokerutils.hand;

import hja.pokerutils.card.Card;

import java.util.ArrayList;

public abstract class Hand implements Comparable<Hand> {
	protected final HandType type;
	protected final ArrayList<Card> hand;
	
	Hand(HandType type, ArrayList<Card> hand) {
		this.type = type;
		this.hand = hand;
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
	
	protected final int compareKickers(Hand other) {
		int initialPos = this.type.firstKicker;
		if (initialPos == 5) return 0;
		
		int compare;
		do {
			Card leftCard = this.hand.get(initialPos);
			Card rightCard = other.hand.get(initialPos);
			compare = leftCard.compareTo(rightCard);
			initialPos += 1;
		} while (compare == 0 && initialPos < 5);
		
		return compare;
	}
	
	protected abstract int compareKernel(Hand other);
	
	public ArrayList<Card> getCards() {
		return new ArrayList<>(this.hand);
	}
	
	public ArrayList<Card> getKernel() {
		int numCardsKernel = type.firstKicker;
		ArrayList<Card> kernel = new ArrayList<>(numCardsKernel);
		
		for (int i = 0; i < numCardsKernel; ++i) {
			kernel.add(this.hand.get(i));
		}
		
		return kernel;
	}
	
	public HandType getType() {
		return this.type;
	}
	
	public abstract String toString();
	
	@Override
	public int hashCode() {
		return hand.hashCode();
	}
}
