package hja.Hand;

import hja.Card;

import java.util.ArrayList;

public class DoublePair extends Hand {
	
	public DoublePair(ArrayList<Card> hand) {
		super(Hand.DOUBLE_PAIR, hand);
	}
	
	@Override
	public int compareTo(Hand o) {
		int compare = Integer.compare(this.type, o.type);
		if(compare == 0){
		
		}
		
		return compare;
	}
}
