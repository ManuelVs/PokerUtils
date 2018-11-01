package hja.pokerutils.Board;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.Utils;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
	public final String name;
	public final Hand hand;
	public final ArrayList<Card> cards;
	
	public Player(String name, Hand hand, ArrayList<Card> cards) {
		this.name = name;
		this.hand = hand;
		this.cards = cards;
	}
	
	@Override
	public int compareTo(Player o) {
		return this.hand.compareTo(o.hand);
	}
	
	@Override
	public String toString() {
		return this.name + " " + Utils.cardsToString(hand.getCards()) + " (" + hand.toString() + ")";
	}
}