package hja.pokerutils.Card;

public final class CardFactory {
	private static Card[][] cards;
	private static Suit[] suits;
	private static Rank[] ranks;
	
	static {
		CardFactory.suits = Suit.values();
		CardFactory.ranks = Rank.values();
		
		CardFactory.cards = new Card[suits.length][ranks.length];
		for (int i = 0; i < suits.length; ++i) {
			for (int j = 0; j < ranks.length; ++j) {
				CardFactory.cards[i][j] = new Card(ranks[j], suits[i]);
			}
		}
	}
	
	public static Card createCard(char rank, char suit) {
		Rank rRank = CardFactory.getRank(rank);
		Suit sSuit = CardFactory.getSuit(suit);
		
		return createCard(rRank, sSuit);
	}
	
	public static Card createCard(Rank rank, Suit suit) {
		int suit_pos = suit.ordinal();
		int rank_pos = rank.ordinal();
		
		return CardFactory.cards[suit_pos][rank_pos];
	}
	
	public static Suit getSuit(char suit) {
		switch (suit) {
			case 'h':
				return Suit.HEARTS;
			case 'd':
				return Suit.DIAMONDS;
			case 's':
				return Suit.SPADES;
			case 'c':
				return Suit.CLUBS;
			default:
				return null;
		}
	}
	
	public static Rank getRank(char rank) {
		switch (rank) {
			case '2':
				return Rank.TWO;
			case '3':
				return Rank.THREE;
			case '4':
				return Rank.FOUR;
			case '5':
				return Rank.FIVE;
			case '6':
				return Rank.SIX;
			case '7':
				return Rank.SEVEN;
			case '8':
				return Rank.EIGHT;
			case '9':
				return Rank.NINE;
			case 'T':
				return Rank.TEN;
			case 'J':
				return Rank.JACK;
			case 'Q':
				return Rank.QUEEN;
			case 'K':
				return Rank.KING;
			case 'A':
				return Rank.ACE;
			default:
				return null;
		}
	}
}