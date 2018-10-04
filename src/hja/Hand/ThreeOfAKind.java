package hja.Hand;

import hja.Card.Card;

import java.util.ArrayList;

public class ThreeOfAKind extends Hand {
	
	public ThreeOfAKind(ArrayList<Card> hand) {
		super(HandType.THREE_OF_A_KIND, hand);
		//Ordenar...
	}
	
	@Override
	public int compareKernel(Hand o) {
		return 0;
		//comparar
	}
}
