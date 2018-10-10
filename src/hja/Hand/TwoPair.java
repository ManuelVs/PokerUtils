package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class TwoPair extends Hand {
	
	public TwoPair(ArrayList<Card> hand) {
		super(HandType.TWO_PAIR, hand);
		//Ordenar...
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
		//comparar
	}
	
	@Override
	public String toString() {
		return "Two pairs of " + hand.get(0).rank.name() + " and " + hand.get(2).rank.name();
	}
}
