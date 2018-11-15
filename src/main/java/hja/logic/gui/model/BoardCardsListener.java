package hja.logic.gui.model;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public interface BoardCardsListener {
	
	void notify(ArrayList<Card> cards);
}
