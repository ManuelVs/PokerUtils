package hja.logic.gui.model;

import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Config {
	private ArrayList<Player> players;
	private ArrayList<Card> boardCards;
	private int phase;
	
	public Config(ArrayList<Card> boardCards, ArrayList<Player> players) {
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
	
	public int getPhase() {
		return phase;
	}
	
	public void setPhase(int phase) {
		this.phase = phase;
	}
}
