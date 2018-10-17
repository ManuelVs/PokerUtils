package hja.pokerutils.Algorithm;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;
import java.util.Collections;

public final class OmahaAlgorithm {
	
	public static Hand calculateHand(ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		CombinationIterator combinationIterator = new CombinationIterator(playerCards, 2);
		
		ArrayList<Card> combination = combinationIterator.next();
		Hand best_hand = calculateHandTwoPlayerCard(combination, boardCards);
		while (combinationIterator.hasNext()){
			combination = combinationIterator.next();
			combination.sort(Collections.reverseOrder());
			Hand current_hand = calculateHandTwoPlayerCard(combination, boardCards);
			
			if(current_hand.compareTo(best_hand) > 0){
				best_hand = current_hand;
			}
		}
		
		return best_hand;
	}
	
	private static Hand calculateHandTwoPlayerCard(ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		CombinationIterator combinationIterator = new CombinationIterator(boardCards, 3);
		
		ArrayList<Card> boardCombination = combinationIterator.next();
		ArrayList<Card> playerAndBoardCards = new ArrayList<>(5);
		playerAndBoardCards.addAll(playerCards);
		playerAndBoardCards.addAll(boardCombination);
		
		Hand best_hand = HoldEmAlgorithm.calculateHand(playerAndBoardCards);
		while (combinationIterator.hasNext()){
			boardCombination = combinationIterator.next();
			boardCombination.sort(Collections.reverseOrder());
			
			playerAndBoardCards = new ArrayList<>(5);
			playerAndBoardCards.addAll(playerCards);
			playerAndBoardCards.addAll(boardCombination);
			Hand current_hand = HoldEmAlgorithm.calculateHand(playerAndBoardCards);
			
			if(current_hand.compareTo(best_hand) > 0){
				best_hand = current_hand;
			}
		}
		
		return best_hand;
	}
}
