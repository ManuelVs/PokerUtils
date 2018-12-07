package hja.pokerutils.Algorithm;

import hja.pokerutils.Algorithm.Combinations.CombinationCalculator;
import hja.pokerutils.Algorithm.Combinations.CombinationIterator;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Hand.Hand;

import java.util.ArrayList;
import java.util.Collections;

public final class OmahaAlgorithm {
	
	public static Hand calculateHand(ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		CombinationCalculator<Card> playerCombinations = new CombinationCalculator<>(playerCards, 2);
		CombinationCalculator<Card> boardCombinations = new CombinationCalculator<>(boardCards, 3);
		
		Hand bestHand = null;
		for(ArrayList<Card> playerCombination : playerCombinations){
			for(ArrayList<Card> boardCombination : boardCombinations){
				Hand hand = HoldEmAlgorithm.calculateHand(playerCombination, boardCombination);
				
				if(bestHand == null || hand.compareTo(bestHand) > 0){
					bestHand = hand;
				}
			}
		}
		
		return bestHand;
	}
}
