package hja.pokerutils.Board;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.Utils;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
	public final String name;
	public final Hand hand;
	public final ArrayList<Card> cards;
	private boolean playing;
	private double equity;
	
	public Player(String name, ArrayList<Card> cards) {
		this(name, null, cards);
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	public double getEquity() {
		return equity;
	}
	
	public void setEquity(double equity) {
		this.equity = equity;
	}
	
	public Player(String name, Hand hand, ArrayList<Card> cards) {
		this.name = name;
		this.hand = hand;
		this.cards = cards;
		this.playing = true;
		this.equity = 0;
	}
	
	@Override
	public int compareTo(Player o) {
		if (this.hand == null) return -1;
		return this.hand.compareTo(o.hand);
	}
	
	@Override
	public String toString() {
		return this.name + " " + Utils.cardsToString(hand.getCards()) + " (" + hand.toString() + ")";
	}
}