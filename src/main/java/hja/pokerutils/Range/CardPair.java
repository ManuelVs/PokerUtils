package hja.pokerutils.Range;

import hja.pokerutils.Card.Rank;

public class CardPair {
	public final Rank firstRank;
	public final Rank secondRank;
	public final boolean isSuited;
	
	public CardPair(Rank firstRank, Rank secondRank, boolean isSuited) {
		int compare = firstRank.compareTo(secondRank);
		if (compare < 0) {
			throw new IllegalArgumentException("First rank lower than second rank");
		}
		else if (compare == 0 && isSuited) {
			throw new IllegalArgumentException("Same rank suited");
		}
		
		this.firstRank = firstRank;
		this.secondRank = secondRank;
		this.isSuited = isSuited;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof CardPair)) return false;
		if (this == obj) return true;
		
		CardPair other = (CardPair) obj;
		return this.firstRank == other.firstRank
				&& this.secondRank == other.secondRank
				&& this.isSuited == other.isSuited;
	}
	
	public String toString() {
		String ret = this.firstRank.rank + "" + this.secondRank.rank;
		if (firstRank == secondRank) return ret;
		else return ret + (this.isSuited ? "s" : "o");
	}
}
