package hja.pokerutils.Range;

import hja.pokerutils.Card.Rank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Range implements Iterable<CardPair> {
	private HashSet<CardPair> cardPairs;
	
	public Range() {
		this.cardPairs = new HashSet<>();
	}
	
	public Range(Rank pairRank, boolean isPairRange) {
		this.cardPairs = new HashSet<>();
		this.cardPairs.add(new CardPair(pairRank, pairRank, false));
		
		if (isPairRange) {
			Rank[] ranks = Rank.values();
			for (int i = pairRank.ordinal() + 1; i <= Rank.ACE.ordinal(); ++i) {
				this.cardPairs.add(new CardPair(ranks[i], ranks[i], false));
			}
		}
	}
	
	public Range(Rank firstRank, Rank secondRank, boolean isSuited, boolean ascending) {
		if (ascending) {
			Rank[] ranks = Rank.values();
			Rank lastRank = ranks[firstRank.ordinal() - 1];
			
			this.rangeInitializer(firstRank, secondRank, lastRank, isSuited);
		}
		else {
			this.cardPairs = new HashSet<>(1);
			this.cardPairs.add(new CardPair(firstRank, secondRank, isSuited));
		}
		
	}
	
	public Range(Rank firstRank, Rank initialRank, Rank lastRank, boolean isSuited) {
		if (initialRank.compareTo(lastRank) < 0)
			this.rangeInitializer(firstRank, initialRank, lastRank, isSuited);
		else this.rangeInitializer(firstRank, lastRank, initialRank, isSuited);
	}
	
	public Range(ArrayList<Range> ranges) {
		this.cardPairs = new HashSet<>();
		for (Range r : ranges) {
			this.cardPairs.addAll(r.cardPairs);
		}
	}
	
	public Range(Range... ranges) {
		this.cardPairs = new HashSet<>();
		for (Range r : ranges) {
			this.cardPairs.addAll(r.cardPairs);
		}
	}
	
	public void add(CardPair cardPair) {
		this.cardPairs.add(cardPair);
	}
	
	public boolean delete(CardPair cardPair) {
		return this.cardPairs.remove(cardPair);
	}
	
	@Override
	public Iterator<CardPair> iterator() {
		return this.cardPairs.iterator();
	}
	
	private void rangeInitializer(Rank firstRank, Rank initialRank, Rank lastRank, boolean isSuited) {
		if (firstRank.compareTo(initialRank) <= 0 || firstRank.compareTo(lastRank) <= 0) {
			throw new IllegalArgumentException("First rank lower than interval");
		}
		
		int numPairs = lastRank.ordinal() - initialRank.ordinal() + 1;
		
		this.cardPairs = new HashSet<>(numPairs);
		
		Rank[] ranks = Rank.values();
		for (int i = initialRank.ordinal(); i <= lastRank.ordinal(); ++i) {
			this.cardPairs.add(new CardPair(firstRank, ranks[i], isSuited));
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		Iterator<CardPair> it = cardPairs.iterator();
		if (it.hasNext()) {
			CardPair cp = it.next();
			sb.append(cp.toString());
			
			while (it.hasNext()) {
				cp = it.next();
				
				sb.append(',');
				sb.append(cp.toString());
			}
		}
		
		return sb.toString();
	}
}
