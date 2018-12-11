package hja.logic.gui.model;

import hja.pokerutils.Algorithm.HandClassifier;
import hja.pokerutils.Board.Player;
import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public class Config {
	private ArrayList<Player> players;
	private ArrayList<Card> boardCards;
	private HandClassifier classifier;
	
	public Config(ArrayList<Card> boardCards, ArrayList<Player> players, HandClassifier classifier) {
		this.players = players;
		this.boardCards = boardCards;
		this.classifier = classifier;
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
	
	public HandClassifier getClassifier() {
		return classifier;
	}
	
	public void setClassifier(HandClassifier classifier) {
		this.classifier = classifier;
	}
}
