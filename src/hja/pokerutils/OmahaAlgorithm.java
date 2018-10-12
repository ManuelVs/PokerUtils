package hja.pokerutils;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;

public class OmahaAlgorithm {
	
	public static Hand calculateHand(ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		if(playerCards.size() == 2){
			return OmahaAlgorithm.calculateHandTwoPlayerCard(playerCards, boardCards);
		}
		else{
			ArrayList<Card> cards_copy = new ArrayList<>(playerCards);
			cards_copy.remove(0);
			Hand best_hand = calculateHand(cards_copy, boardCards);
			
			for (int i = 1; i < playerCards.size(); ++i) {
				cards_copy = new ArrayList<>(playerCards);
				cards_copy.remove(i);
				
				Hand actual_hand = calculateHand(cards_copy, boardCards);
				
				if (actual_hand.compareTo(best_hand) > 0) {
					best_hand = actual_hand;
				}
			}
			
			return best_hand;
		}
	}
	
	private static Hand calculateHandTwoPlayerCard(ArrayList<Card> playerCards, ArrayList<Card> boardCards){
		if(boardCards.size() == 3){
			ArrayList<Card> cards = new ArrayList<>(7);
			
			cards.addAll(playerCards);
			cards.addAll(boardCards);
			
			return HoldEmAlgorithm.calculateHand(cards);
		}
		else{
			ArrayList<Card> cards_copy = new ArrayList<>(boardCards);
			cards_copy.remove(0);
			Hand best_hand = calculateHandTwoPlayerCard(playerCards, cards_copy);
			
			for (int i = 1; i < playerCards.size(); ++i) {
				cards_copy = new ArrayList<>(boardCards);
				cards_copy.remove(i);
				
				Hand actual_hand = calculateHandTwoPlayerCard(playerCards, cards_copy);
				
				if (actual_hand.compareTo(best_hand) > 0) {
					best_hand = actual_hand;
				}
			}
			
			return best_hand;
		}
	}
}
