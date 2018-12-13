package hja.pokerutils.card;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardFactoryTest {
	private static Stream<Arguments> cardsProvider() {
		return Stream.of(
			Arguments.of('A', 'h', Rank.ACE, Suit.HEARTS),
			Arguments.of('K', 's', Rank.KING, Suit.SPADES),
			Arguments.of('Q', 'c', Rank.QUEEN, Suit.CLUBS),
			Arguments.of('J', 'd', Rank.JACK, Suit.DIAMONDS),
			Arguments.of('T', 'd', Rank.TEN, Suit.DIAMONDS),
			Arguments.of('9', 's', Rank.NINE, Suit.SPADES),
			Arguments.of('8', 'h', Rank.EIGHT, Suit.HEARTS),
			Arguments.of('7', 's', Rank.SEVEN, Suit.SPADES),
			Arguments.of('6', 'c', Rank.SIX, Suit.CLUBS),
			Arguments.of('5', 'd', Rank.FIVE, Suit.DIAMONDS),
			Arguments.of('4', 's', Rank.FOUR, Suit.SPADES),
			Arguments.of('3', 'c', Rank.THREE, Suit.CLUBS),
			Arguments.of('2', 'd', Rank.TWO, Suit.DIAMONDS)
		);
	}
	
	@ParameterizedTest(name = "{index} => {0}{1}")
	@MethodSource("cardsProvider")
	void createCardTest(char rank, char suit, Rank realRank, Suit realSuit) {
		Card card = CardFactory.createCard(rank, suit);
		
		assertEquals(realRank, card.rank);
		assertEquals(realSuit, card.suit);
	}
}