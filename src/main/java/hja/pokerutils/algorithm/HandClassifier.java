package hja.pokerutils.algorithm;

import hja.pokerutils.card.Card;
import hja.pokerutils.hand.Hand;

import java.util.ArrayList;

public interface HandClassifier {
	
	/**
	 * Calculates the best hand given the player cards and the board cards
	 *
	 * @param playerCards Player cards.
	 * @param boardCards  board cards
	 * @return The best hand
	 */
	Hand calculateHand(ArrayList<Card> playerCards, ArrayList<Card> boardCards);
}
