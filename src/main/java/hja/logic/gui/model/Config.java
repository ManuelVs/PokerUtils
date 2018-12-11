package hja.logic.gui.model;

import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Config {
	private ArrayList<Player> players;
	private ArrayList<Card> boardCards;
	
	public Config(ArrayList<Player> players, ArrayList<Card> boardCards) {
		this.players = players;
		this.boardCards = boardCards;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public ArrayList<Card> getBoardCards() {
		return boardCards;
	}
	
	public void setBoardCards(ArrayList<Card> boardCards) {
		this.boardCards = boardCards;
	}
}
