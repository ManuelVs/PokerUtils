package hja.pokerutils.Board;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.Utils;

import java.util.ArrayList;

public final class Player implements Comparable<Player> {
	private final int playerNumber;
	private final Hand hand;
	private final ArrayList<Card> cards;
	private double equity;
	
	public Player(int playerNumber, ArrayList<Card> cards) {
		this(playerNumber, null, cards);
	}
	
	public Player(int playerNumber, Hand hand, ArrayList<Card> cards) {
		this.playerNumber = playerNumber;
		this.hand = hand;
		this.cards = cards;
		this.equity = 0;
	}
	
	@Override
	public int compareTo(Player o) {
		if (this.hand == null) return -1;
		return this.hand.compareTo(o.hand);
	}
	
	@Override
	public String toString() {
		return "J" + this.playerNumber + " " + Utils.cardsToString(hand.getCards()) + " (" + hand.toString() + ")";
	}
	
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public double getEquity() {
		return equity;
	}
	
	public void setEquity(double equity) {
		this.equity = equity;
	}
}