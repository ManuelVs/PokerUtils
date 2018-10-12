package hja.pokerutils.Parser;

import hja.pokerutils.Card.Card;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Card.Suit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardListParserTest {
	private static Stream<Arguments> cardListsProvider() {
		return Stream.of(
				Arguments.of("AhAcQhJhTh", 5, new ArrayList<>(Arrays.asList(
						new Card(Rank.ACE, Suit.HEARTS),
						new Card(Rank.ACE, Suit.CLUBS),
						new Card(Rank.QUEEN, Suit.HEARTS),
						new Card(Rank.JACK, Suit.HEARTS),
						new Card(Rank.TEN, Suit.HEARTS)
				))),
				
				Arguments.of("As2s3h4dJd", 5, new ArrayList<>(Arrays.asList(
						new Card(Rank.ACE, Suit.SPADES),
						new Card(Rank.TWO, Suit.SPADES),
						new Card(Rank.THREE, Suit.HEARTS),
						new Card(Rank.FOUR, Suit.DIAMONDS),
						new Card(Rank.JACK, Suit.DIAMONDS)
				))),
				
				Arguments.of("AhAcAs4dJd", 5, new ArrayList<>(Arrays.asList(
						new Card(Rank.ACE, Suit.HEARTS),
						new Card(Rank.ACE, Suit.CLUBS),
						new Card(Rank.ACE, Suit.SPADES),
						new Card(Rank.FOUR, Suit.DIAMONDS),
						new Card(Rank.JACK, Suit.DIAMONDS)
				))),
				
				Arguments.of("5dKs6cTh9h", 5, new ArrayList<>(Arrays.asList(
						new Card(Rank.FIVE, Suit.DIAMONDS),
						new Card(Rank.KING, Suit.SPADES),
						new Card(Rank.SIX, Suit.CLUBS),
						new Card(Rank.TEN, Suit.HEARTS),
						new Card(Rank.NINE, Suit.HEARTS)
				))),
				
				Arguments.of("2h2d2c2s", 4, new ArrayList<>(Arrays.asList(
						new Card(Rank.TWO, Suit.HEARTS),
						new Card(Rank.TWO, Suit.DIAMONDS),
						new Card(Rank.TWO, Suit.CLUBS),
						new Card(Rank.TWO, Suit.SPADES)
				)))
		);
	}
	
	@ParameterizedTest(name = "{index} => {0}")
	@MethodSource("cardListsProvider")
	void parseListCardTest(String sCards, int numCards, ArrayList<Card> aCards) throws IOException {
		StringReader reader = new StringReader(sCards);
		ArrayList<Card> cards = CardListParser.parseListCard(reader, numCards);
		
		assertEquals(aCards, cards);
	}
	
}