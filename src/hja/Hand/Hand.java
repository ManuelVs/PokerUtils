package hja.Hand;

import hja.Card;

import java.util.ArrayList;

public abstract class Hand implements Comparable<Hand> {
	protected static final int HIGH_CARD       = 1;
	protected static final int PAIR            = 2;
	protected static final int DOUBLE_PAIR     = 3;
	protected static final int THREE_OF_A_KIND = 4;
	protected static final int STRAIGHT        = 5;
	protected static final int FLUSH           = 6;
	protected static final int FULL_HOUSE      = 7;
	protected static final int FOUR_OF_A_KIND  = 8;
	protected static final int STRAIGHT_FLUSH  = 9;
	protected static final int ROYAL_FLUSH     = 10;
	
	protected int type;
	protected ArrayList<Card> hand;
	
	Hand(int type, ArrayList<Card> hand) {
		this.type = type;
		this.hand = hand;
	}
}
