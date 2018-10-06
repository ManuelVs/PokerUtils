package hja.Parser;

import hja.Card.Card;
import hja.Card.CardFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class CardListParser {
	
	public static ArrayList<Card> parseListCard(InputStreamReader stream, int numCards) throws IOException {
		ArrayList<Card> arr = new ArrayList<>(numCards);
		
		for (int i = 0; i < numCards; ++i) {
			Card card = parseCard(stream);
			arr.set(i, card);
		}
		
		return arr;
	}
	
	private static Card parseCard(InputStreamReader stream) throws IOException {
		char rank = (char) stream.read();
		char suit = (char) stream.read();
		
		return CardFactory.createCard(rank, suit);
	}
}
