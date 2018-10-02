package hja;

public class Card implements Comparable<Card> {
	protected int rank;
	protected char suit;
	
	public Card(int rank, char suit){
		this.rank = rank;
		this.suit = suit;
	}
	
	@Override
	public int compareTo(Card o) {
		return Integer.compare(this.rank, o.rank);
	}
}
