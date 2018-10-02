package hja.Hand;

import hja.Card;

import java.util.ArrayList;

public class Pair extends Hand {
	
	public Pair(ArrayList<Card> hand) {
		super(Hand.PAIR, hand);
	}
	
	@Override
	public int compareTo(Hand o) {
		int compare = Integer.compare(this.type, o.type);
		if(compare == 0){
		
		}
		
		return compare;
	}
}
