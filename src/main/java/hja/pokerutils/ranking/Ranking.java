package hja.pokerutils.ranking;

import hja.pokerutils.range.CardPair;
import hja.pokerutils.range.Range;

import java.util.ArrayList;

public class Ranking {
	private final ArrayList<CardPair> cardPairArray;
	
	public Ranking() {
		this.cardPairArray = new ArrayList<>();
	}
	
	public Ranking(ArrayList<CardPair> cardPairArray) {
		this.cardPairArray = cardPairArray;
	}
	
	public Range selectTopCardPairs(double percentage) {
		long numCards = 0;
		if (percentage > 0) {
			numCards = Math.round(this.cardPairArray.size() * percentage / 100);
		}
		
		Range out = new Range();
		
		for (int i = 0; i < numCards; ++i) {
			out.add(this.cardPairArray.get(i));
		}
		
		return out;
	}
}
