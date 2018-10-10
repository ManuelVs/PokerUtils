package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class Pair extends Hand {
	
	public Pair(ArrayList<Card> hand) {
		super(HandType.PAIR, hand);
		//Ordenar...
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
		//comparar
	}
	
	@Override
	public String toString() {
		return "Pair of " + hand.get(0).rank.name();
	}
}
