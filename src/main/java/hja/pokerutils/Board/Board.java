package hja.pokerutils.Board;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Board {
	public final ArrayList<Card> boardCards;
	public final ArrayList<Player> players;
	private int phase;
	
	public Board(ArrayList<Card> boardCards, ArrayList<Player> players) {
		this.boardCards = boardCards;
		this.players = players;
	}
	
	public int getPhase() {
		return phase;
	}
	
	public void setPhase(int phase) {
		this.phase = phase;
	}
}