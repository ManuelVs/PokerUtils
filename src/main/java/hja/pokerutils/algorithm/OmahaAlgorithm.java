package hja.pokerutils.algorithm;

import hja.pokerutils.algorithm.combinations.CombinationCalculator;
import hja.pokerutils.card.Card;
import hja.pokerutils.hand.Hand;

import java.util.ArrayList;

public final class OmahaAlgorithm implements HandClassifier {
	private static final HandClassifier holdEmEval = new HoldEmAlgorithm();
	
	public final Hand calculateHand(ArrayList<Card> playerCards, ArrayList<Card> boardCards) {
		CombinationCalculator<Card> playerCombinations = new CombinationCalculator<>(playerCards, 2);
		CombinationCalculator<Card> boardCombinations = new CombinationCalculator<>(boardCards, 3);
		
		Hand bestHand = null;
		for (ArrayList<Card> playerCombination : playerCombinations) {
			for (ArrayList<Card> boardCombination : boardCombinations) {
				Hand hand = holdEmEval.calculateHand(playerCombination, boardCombination);
				
				if (bestHand == null || hand.compareTo(bestHand) > 0) {
					bestHand = hand;
				}
			}
		}
		
		return bestHand;
	}
}
