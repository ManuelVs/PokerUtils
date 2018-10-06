package hja.Card;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTest {
	private static final Card ace = new Card(Rank.ACE, Suit.HEARTS);
	private static final Card king = new Card(Rank.KING, Suit.CLUBS);
	private static final Card queen = new Card(Rank.QUEEN, Suit.SPADES);
	private static final Card jack = new Card(Rank.JACK, Suit.DIAMONDS);
	private static final Card ten = new Card(Rank.TEN, Suit.HEARTS);
	
	private static Stream<Arguments> compareProvider() {
		return Stream.of(
				Arguments.of(ace, king),
				Arguments.of(ace, jack),
				Arguments.of(queen, jack),
				Arguments.of(queen, ten),
				Arguments.of(jack, ten)
		);
	}
	
	private static Stream<Arguments> compareEqualProvider() {
		return Stream.of(
				Arguments.of(ace, ace),
				Arguments.of(jack, jack),
				Arguments.of(queen, queen),
				Arguments.of(ten, ten),
				Arguments.of(king, king)
		);
	}
	
	@ParameterizedTest(name = "{index} => left={0}, right={1}")
	@MethodSource("compareProvider")
	void compareToTest(Card left, Card right){
		int compareGreater = left.compareTo(right);
		assertTrue(compareGreater > 0);
		
		int compareLess = right.compareTo(left);
		assertTrue(compareLess < 0);
	}
	
	@ParameterizedTest(name = "{index} => left={0}, right={1}")
	@MethodSource("compareEqualProvider")
	void compareToEqualTest(Card left, Card right){
		int compare = left.compareTo(right);
		
		assertTrue(compare == 0);
	}
}