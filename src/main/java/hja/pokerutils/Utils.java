package hja.pokerutils;

import hja.pokerutils.Card.Card;

import java.util.ArrayList;

public final class Utils {
	
	public static String cardsToString(ArrayList<Card> cards) {
		StringBuilder sb = new StringBuilder();
		
		for (Card card : cards) {
			sb.append(card);
		}
		
		return sb.toString();
	}
	
	public static void swap(ArrayList<Card> hand, int i, int j) {
		Card firstCard = hand.get(i);
		Card secondCard = hand.get(j);
		
		hand.set(i, secondCard);
		hand.set(j, firstCard);
	}
}
