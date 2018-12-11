package hja.pokerutils;

import hja.pokerutils.Card.Card;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;

import static hja.pokerutils.Parser.CardListParser.parseListCard;

public final class Utils {
	
	public static String cardsToString(ArrayList<Card> cards) {
		StringBuilder sb = new StringBuilder();
		
		for (Card card : cards) {
			sb.append(card);
		}
		
		return sb.toString();
	}
}
