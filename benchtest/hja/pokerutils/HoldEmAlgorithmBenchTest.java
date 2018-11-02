package hja.pokerutils;

import hja.pokerutils.Algorithm.CombinationIterator;
import hja.pokerutils.Algorithm.HoldEmAlgorithm;
import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;
import hja.pokerutils.Hand.Hand;
import hja.pokerutils.Hand.RoyalFlush;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoldEmAlgorithmBenchTest {
	
	@Test
	void allCards() {
		ArrayList<Card> allCards = new ArrayList<>();
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();
		
		for (Rank rank : ranks) {
			for (Suit suit : suits) {
				allCards.add(new Card(rank, suit));
			}
		}
		
		Hand bestHand = HoldEmAlgorithm.calculateHand(allCards);
		assertEquals(RoyalFlush.class, bestHand.getClass());
	}
	
	@Test
	void allHands(){
		ArrayList<Card> allCards = new ArrayList<>();
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();
		
		for (Rank rank : ranks) {
			for (Suit suit : suits) {
				allCards.add(new Card(rank, suit));
			}
		}
		
		ArrayList<Hand> allHands = new ArrayList<>();
		CombinationIterator iterator = new CombinationIterator(allCards, 5);
		ArrayList<Card> cards = iterator.next();
		Hand hand = HoldEmAlgorithm.calculateHand(cards);
		allHands.add(hand);
		
		while (iterator.hasNext()){
			cards = iterator.next();
			hand = HoldEmAlgorithm.calculateHand(cards);
			allHands.add(hand);
		}
		
		assertEquals(2598960, allHands.size());
	}
}
