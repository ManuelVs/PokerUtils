package hja.pokerutils.Board;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.Utils;

import java.util.ArrayList;

public final class Player {
	private final int playerNumber;
	private ArrayList<Card> cards;
	private double equity;
	
	public Player(int playerNumber, ArrayList<Card> cards) {
		this.playerNumber = playerNumber;
		this.cards = cards;
		this.equity = 0;
	}
	
	@Override
	public String toString() {
		return "J" + this.playerNumber;
	}
	
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public double getEquity() {
		return equity;
	}
	
	public void setEquity(double equity) {
		this.equity = equity;
	}
}