package hja.pokerutils.Range;

import hja.pokerutils.Card.Rank;

import java.util.ArrayList;
import java.util.Iterator;

public class Range implements Iterable<CardPair> {
	private ArrayList<CardPair> cardPairs;
	
	public Range(Rank pairRank, boolean isPairRange){
		this.cardPairs = new ArrayList<>();
		this.cardPairs.add(new CardPair(pairRank, pairRank, false));
		
		if(isPairRange) {
			Rank[] ranks = Rank.values();
			for(int i = pairRank.ordinal() + 1; i <= Rank.ACE.ordinal(); ++i){
				this.cardPairs.add(new CardPair(ranks[i], ranks[i], false));
			}
		}
	}
	
	public Range(Rank firstRank, Rank secondRank, boolean isSuited, boolean ascending){
		if(ascending){
			Rank[] ranks = Rank.values();
			Rank lastRank = ranks[firstRank.ordinal() - 1];
			
			this.rangeInitializer(firstRank, secondRank, lastRank, isSuited);
		}
		else {
			this.cardPairs = new ArrayList<>(1);
			this.cardPairs.add(new CardPair(firstRank, secondRank, isSuited));
		}

	}
	
	public Range(Rank firstRank, Rank initialRank, Rank lastRank, boolean isSuited){
		this.rangeInitializer(firstRank, initialRank, lastRank, isSuited);
	}
	
	public Range(ArrayList<Range> ranges){
		this.cardPairs = new ArrayList<>();
		for(Range r : ranges){
			this.cardPairs.addAll(r.cardPairs);
		}
	}
	
	@Override
	public Iterator<CardPair> iterator() {
		return this.cardPairs.iterator();
	}
	
	private void rangeInitializer(Rank firstRank, Rank initialRank, Rank lastRank, boolean isSuited){
		if(firstRank.compareTo(initialRank) <= 0 || firstRank.compareTo(lastRank) <= 0){
			throw new IllegalArgumentException("First rank lower than interval");
		}
		
		int numPairs = lastRank.ordinal() - initialRank.ordinal() + 1;
		
		this.cardPairs = new ArrayList<>(numPairs);
		
		Rank[] ranks = Rank.values();
		for(int i = initialRank.ordinal(); i <= lastRank.ordinal(); ++i){
			this.cardPairs.add(new CardPair(firstRank, ranks[i], isSuited));
		}
	}
}
