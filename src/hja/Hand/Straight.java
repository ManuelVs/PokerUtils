package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class Straight extends Hand {
	
	public Straight(ArrayList<Card> hand) {
		super(HandType.STRAIGHT, hand);
		//Ordenar...
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
		//comparar
	}
	
	@Override
	public String toString() {
		return "Straight ended in " + hand.get(0).rank.name();
	}
}
