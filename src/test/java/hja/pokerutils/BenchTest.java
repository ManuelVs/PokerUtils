package hja.pokerutils;

import hja.pokerutils.Algorithm.Combinations.CombinationCalculator;
import hja.pokerutils.Algorithm.HoldEmAlgorithm;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Hand.Hand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenchTest {
	
	@Test
	public void benchTest() {
		ArrayList<Card> cards = CardFactory.getAllCards();
		ArrayList<Hand> hands = new ArrayList<>(2598960);
		
		CombinationCalculator<Card> combinations = new CombinationCalculator<>(cards, 5);
		for (ArrayList<Card> cards1 : combinations) {
			hands.add(HoldEmAlgorithm.calculateHand(cards1));
		}
		
		assertEquals(2598960 , hands.size());
	}
}
