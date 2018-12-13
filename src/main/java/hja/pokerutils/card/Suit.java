package hja.pokerutils.card;

public enum Suit {
	HEARTS('h'), DIAMONDS('d'), SPADES('s'), CLUBS('c');
	
	public final char suit;
	
	Suit(char suit) {
		this.suit = suit;
	}
}