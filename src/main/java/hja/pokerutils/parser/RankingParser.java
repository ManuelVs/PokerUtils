package hja.pokerutils.parser;

import hja.pokerutils.card.CardFactory;
import hja.pokerutils.range.CardPair;
import hja.pokerutils.ranking.Ranking;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public final class RankingParser {
	public static Ranking parseRanking(Reader reader, int length) throws IOException {
		char[] cRanking = new char[length];
		reader.read(cRanking);
		
		String ranking = new String(cRanking);
		String[] parts = ranking.split(",");
		
		ArrayList<CardPair> cardPairArray = new ArrayList<>();
		for (String p : parts) {
			boolean isSuited = false;
			if (p.length() == 3 && p.charAt(2) == 's') {
				isSuited = true;
			}
			CardPair cp = new CardPair(CardFactory.getRank(p.charAt(0)), CardFactory.getRank(p.charAt(1)), isSuited);
			
			cardPairArray.add(cp);
		}
		
		return new Ranking(cardPairArray);
	}
}