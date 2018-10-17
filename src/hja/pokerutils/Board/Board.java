package hja.pokerutils.Board;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Board {
	public final ArrayList<Card> boardCards;
	public final ArrayList<Player> players;
	
	public Board(ArrayList<Card> boardCards, ArrayList<Player> players) {
		this.boardCards = boardCards;
		this.players = players;
	}
}