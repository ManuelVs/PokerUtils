package hja.Card;

public class Card implements Comparable<Card> {
	public final Rank rank;
	public final Suit suit;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	@Override
	public int compareTo(Card o) {
		return this.rank.compareTo(o.rank);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Card)) return false;
		if (this == obj) return true;
		
		Card other = (Card) obj;
		return other.rank == this.rank && other.suit == this.suit;
	}
	
	@Override
	public String toString() {
		return rank.rank + "" + suit.suit;
	}
}
