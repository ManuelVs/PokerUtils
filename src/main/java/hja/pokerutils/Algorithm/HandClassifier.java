package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;

public interface HandClassifier {
	
	/**
	 * Calculates the best hand given the player cards and the board cards
	 *
	 * @param playerCards Player cards.
	 * @param boardCards  Board cards
	 * @return The best hand
	 */
	Hand calculateHand(ArrayList<Card> playerCards, ArrayList<Card> boardCards);
}
