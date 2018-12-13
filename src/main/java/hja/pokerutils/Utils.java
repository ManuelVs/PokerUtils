package hja.pokerutils;

import hja.pokerutils.card.Card;

import java.util.ArrayList;

public final class Utils {
	
	public static String cardsToString(ArrayList<Card> cards) {
		StringBuilder sb = new StringBuilder();
		
		for (Card card : cards) {
			sb.append(card);
		}
		
		return sb.toString();
	}
}
