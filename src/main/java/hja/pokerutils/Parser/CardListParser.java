package hja.pokerutils.Parser;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.CardFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;

public final class CardListParser {
	
	public static ArrayList<Card> parseListCard(Reader reader, int numCards) throws IOException {
		ArrayList<Card> arr = new ArrayList<>(numCards);
		
		for (int i = 0; i < numCards; ++i) {
			Card card = parseCard(reader);
			arr.add(card);
		}
		
		return arr;
	}
	
	private static Card parseCard(Reader reader) throws IOException {
		char rank = (char) reader.read();
		char suit = (char) reader.read();
		
		try {
			return CardFactory.createCard(rank, suit);
		}
		catch (Exception e) {
			throw new IOException("Bad card: " + rank + suit);
		}
	}
	
	public static ArrayList<Card> parseListCards(String cards) throws ParseException {
		try{
			int numCards = cards.length() / 2;
			StringReader stringReader = new StringReader(cards);
			
			return parseListCard(stringReader, numCards);
		}
		catch (IOException e) {
			throw new ParseException(e.getMessage(), -1);
		}
	}
}
