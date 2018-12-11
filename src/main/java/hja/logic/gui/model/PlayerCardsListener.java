package hja.logic.gui.model;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public interface PlayerCardsListener {
	void notify(int player, String cards);
}
