package hja.pokerutils.Hand;

public enum HandType {
	HIGH_CARD(0), PAIR(2), TWO_PAIR(4), THREE_OF_A_KIND(3), STRAIGHT(5),
	FLUSH(5), FULL_HOUSE(5), FOUR_OF_A_KIND(4), STRAIGHT_FLUSH(5), ROYAL_FLUSH(5);
	
	public final int firstKicker;
	
	HandType(int firstKicker) {
		this.firstKicker = firstKicker;
	}
}